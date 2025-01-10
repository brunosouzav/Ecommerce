package com.ecommerce.fit.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.fit.exceptions.OrderProblemException;
import com.ecommerce.fit.models.Order;
import com.ecommerce.fit.models.OrderItem;
import com.ecommerce.fit.repositories.OrderRepository;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodsRequest;
import com.mercadopago.client.preference.PreferencePaymentTypeRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;


@Service
public class PaymentService {

	@Autowired
	private OrderRepository orderRepository;
	
	public String createPayment(Long orderId) throws MPException, MPApiException {
		
		Order order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new OrderProblemException("Order não encontrada"));


	    MercadoPagoConfig.setAccessToken("APP_USR-4513655237209123-010409-a97fb0a6cf3eb43731cdc5762463f022-2189127598");

	 
	    PreferenceClient client = new PreferenceClient();

	    
	    List<PreferenceItemRequest> items = new ArrayList<>();
	    
	    for (OrderItem orderItem : order.getItems()) {
	        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
	                .id(order.getId().toString())
	                .title(orderItem.getProduct().getName())
	                .description(orderItem.getProduct().getDescription())
	                .quantity(1)
	                .currencyId("BRL")
	                .unitPrice(BigDecimal.valueOf(orderItem.getPrice()))
	                .build();

	        items.add(itemRequest);
	        
	       
	    } 
	    
	    
	    List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
		excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());
				
		List<PreferencePaymentTypeRequest> allowedPaymentTypes = new ArrayList<>();
        allowedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("pix").build());
	
	 
	    
    

	    PreferenceRequest preferenceRequest = PreferenceRequest.builder()
	    		
	    		.backUrls(
	                PreferenceBackUrlsRequest.builder()
	                    .success("https://test.com/success") 
	                    .failure("https://test.com/failure") 
	                    .pending("https://test.com/pending")
	                    
	                    .build())
	            .payer(
	                PreferencePayerRequest.builder()
	                    .name(order.getUser().getName()) 
	                   
	                    .build())
	            .items(items) 
	            .paymentMethods(PreferencePaymentMethodsRequest.builder()
	                    .excludedPaymentTypes(excludedPaymentTypes) 
	                    .build())
	            .build();

	    Preference preference = client.create(preferenceRequest);

	  
	    return preference.getInitPoint(); 

	   
	}
}
