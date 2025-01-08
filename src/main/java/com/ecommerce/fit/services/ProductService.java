package com.ecommerce.fit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.fit.exceptions.ProductNotFoundException;
import com.ecommerce.fit.models.Product;
import com.ecommerce.fit.repositories.ProductRepository;

@Service
public class ProductService {

	
	@Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public void updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }

        productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        productRepository.delete(product);
    }
    
}
