package com.ecommerce.fit.models.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemPK {
	 
	@Column(name = "order_id")
	 private Long orderId;
	
	@Column(name = "product_id")
	 private Long productId;
	
}
