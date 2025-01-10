package com.ecommerce.fit.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.fit.enums.OrderStatus;
import com.ecommerce.fit.models.Order;
import com.ecommerce.fit.repositories.OrderRepository;
import com.mercadopago.net.HttpStatus;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	  @Autowired
	    private OrderRepository orderRepository;
	  @PostMapping
	    public ResponseEntity<String> handleNotification(@RequestBody Map<String, Object> notificationData) {
	        try {
	           
	            System.out.println("Notificação recebida: " + notificationData);

	           
	            String topic = (String) notificationData.get("topic");
	            String paymentId = (String) notificationData.get("id");

	            if ("payment".equals(topic)) {
	                 
	            	System.out.println("Pagamento recebido, ID: " + paymentId);

	                
	                updateOrderStatus(paymentId);
	            }

	          
	            return ResponseEntity.ok("Notificação processada com sucesso!");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar notificação");
	        }
	    }

	  private void updateOrderStatus(String paymentId) {
	       
	        Long orderId = Long.valueOf(paymentId); 

	       
	        Order order = orderRepository.findById(orderId)
	                .orElseThrow(() -> new RuntimeException("Order não encontrada com ID: " + orderId));

	        order.setOrderStatus(OrderStatus.PAID); 

	       
	        orderRepository.save(order);

	        System.out.println("Status da ordem atualizado para: " + OrderStatus.PAID);
	    }

}
