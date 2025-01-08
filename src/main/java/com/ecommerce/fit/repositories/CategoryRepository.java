package com.ecommerce.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.fit.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
