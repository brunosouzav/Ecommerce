package com.ecommerce.fit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.fit.exceptions.CategoryProblemException;
import com.ecommerce.fit.exceptions.ProductProblemException;
import com.ecommerce.fit.models.Category;
import com.ecommerce.fit.models.Product;
import com.ecommerce.fit.repositories.CategoryRepository;
import com.ecommerce.fit.repositories.ProductRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public void addProduct(Long categoryId, Long productId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryProblemException("Categoria com id " + categoryId + " não foi encontrada"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductProblemException("Produto com id " + productId + " não foi encontrado"));

        product.setCategory(category);
        category.getProducts().add(product);

        productRepository.save(product);
        categoryRepository.save(category);
    }

    public void removeProduct(Long categoryId, Long productId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryProblemException("Categoria com id " + categoryId + " não foi encontrada"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductProblemException("Produto com id " + productId + " não foi encontrado"));

        category.getProducts().remove(product);
        product.setCategory(null);

        productRepository.delete(product);
        categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryProblemException("Categoria com id " + id + " não foi encontrada"));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}