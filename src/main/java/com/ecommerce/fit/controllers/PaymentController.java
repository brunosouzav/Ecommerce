package com.ecommerce.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.fit.services.PaymentService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.HttpStatus;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	

    @Autowired
    private PaymentService paymentService;
	 
    @PostMapping("/create/{orderId}")
	    public ResponseEntity<String> createPayment(@PathVariable Long orderId) throws MPException, MPApiException {
	        
	            String paymentUrl = paymentService.createPayment(orderId);

	             return ResponseEntity.status(HttpStatus.CREATED).body(paymentUrl);
	       
	        
	    }
}
