package com.ecommerce.fit.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.fit.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findByName(String name);
}
