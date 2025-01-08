package com.ecommerce.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.fit.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
