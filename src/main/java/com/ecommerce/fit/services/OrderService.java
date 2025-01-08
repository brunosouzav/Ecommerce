package com.ecommerce.fit.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.fit.enums.OrderStatus;
import com.ecommerce.fit.exceptions.ProductProblemException;
import com.ecommerce.fit.exceptions.UserProblemException;
import com.ecommerce.fit.models.Order;
import com.ecommerce.fit.models.OrderItem;
import com.ecommerce.fit.models.Product;
import com.ecommerce.fit.models.User;
import com.ecommerce.fit.models.pk.OrderItemPK;
import com.ecommerce.fit.record.OrderRequest;
import com.ecommerce.fit.repositories.OrderItemRepository;
import com.ecommerce.fit.repositories.OrderRepository;
import com.ecommerce.fit.repositories.ProductRepository;
import com.ecommerce.fit.repositories.UserRepository;

@Service
public class OrderService {
	
	 @Autowired
	    private OrderRepository orderRepository;

	 @Autowired 
	 	private ProductRepository productRepository;
	 
	 @Autowired
	 	private UserRepository userRepository;
	 
	 @Autowired
	 	private OrderItemRepository orderItemRepository;
	    
	 	public void createOrder(OrderRequest orderRequest) {
	    
	 		 Product product = productRepository.findById(orderRequest.productId())
	                 .orElseThrow(() -> new ProductProblemException("Produto não encontrado"));


	         OrderItem orderItem = new OrderItem();
	         orderItem.setProduct(product);
	         orderItem.setQuantity(orderRequest.quantity());
	         orderItem.calculatePrice();
	         
	         OrderItemPK orderItemPK = new OrderItemPK();
	         orderItemPK.setProductId(product.getId());
	         orderItem.setId(orderItemPK);
	         
	         User user = userRepository.findById(orderRequest.productId())
	                 .orElseThrow(() -> new UserProblemException("Usuario não encontrado"));
	         
	         Order order = new Order();
	         order.setUser(user); 
	         order.setMoment(Instant.now());  
	         order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
	         order.getItems().add(orderItem);
	         
	         orderRepository.save(order);
	         
	         orderItem.getId().setOrderId(order.getId());
	         
	         orderItemRepository.save(orderItem);

	         
	       
	    }

	    
	 	public List<Order> findAll() {
	        return orderRepository.findAll();
	    }

	    public Order findById(Long id) {
	        return orderRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
	    }

	    public void updateOrderStatus(Long id) {
	        Order existingOrder = orderRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
	        existingOrder.setOrderStatus(existingOrder.getOrderStatus());
	        orderRepository.save(existingOrder);
	    }

	    public void deleteOrder(Long id) {
	        Order order = orderRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
	        orderRepository.delete(order);
	    }
}
