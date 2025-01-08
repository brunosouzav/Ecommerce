package com.ecommerce.fit.models;

import com.ecommerce.fit.models.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_order_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem  {

	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();

	private Integer quantity;
	private Double price;
	
	@ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;
	

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
    
    @JsonBackReference
    public Order getOrder () {
    	return order;
    }
    
    @PrePersist
    public void calculatePrice() {
        if (this.product != null && this.quantity != null) {
            this.price = this.product.getPrice() * this.quantity;  
        }
    
    }
}
