package com.ecommerce.fit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.fit.models.Category;
import com.ecommerce.fit.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody Category category) {
        categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/add-product/{categoryId}/{productId}")
    public ResponseEntity<Void> addProduct(@PathVariable Long categoryId, @PathVariable Long productId) {
        categoryService.addProduct(categoryId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category foundCategory = categoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> allCategories = categoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allCategories);
    }

    @DeleteMapping("/remove-product/{categoryId}/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long categoryId, @PathVariable Long productId) {
        categoryService.removeProduct(categoryId, productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
