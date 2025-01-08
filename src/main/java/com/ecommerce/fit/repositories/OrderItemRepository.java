package com.ecommerce.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.fit.models.OrderItem;
import com.ecommerce.fit.models.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
