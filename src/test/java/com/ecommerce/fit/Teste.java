package com.ecommerce.fit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.fit.exceptions.CategoryProblemException;
import com.ecommerce.fit.exceptions.ProductProblemException;
import com.ecommerce.fit.models.Category;
import com.ecommerce.fit.models.Product;
import com.ecommerce.fit.models.User;
import com.ecommerce.fit.repositories.CategoryRepository;
import com.ecommerce.fit.repositories.ProductRepository;
import com.ecommerce.fit.repositories.UserRepository;
import com.ecommerce.fit.services.CategoryService;
import com.ecommerce.fit.services.OrderService;
import com.ecommerce.fit.services.ProductService;
import com.ecommerce.fit.services.UserService;

@ExtendWith(MockitoExtension.class)
public class Teste {

	@Mock
	 UserRepository userRepository;
	
	@InjectMocks
	 UserService userService;
	
	@Mock 
	 ProductRepository productRepository;
	
	@InjectMocks
	ProductService productService;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@InjectMocks
	CategoryService categoryService;
	

	
	@InjectMocks
	OrderService orderService;
	
	Product productMock;
	
	User userMock;
	
    Category mockCategory;
    
  
	
	@BeforeEach
	void setUp () {
	
		
		 userMock = new User(1L, "Bruno Souza", "123456789", "bruno@89", null);
		 productMock = new Product(1L, "Camisa","CamisaT-shhor", 50.00, null);
		 mockCategory = new Category(1L, "Vestuario", "Moda", new ArrayList<>());
		
		
	}
	
	
	@Test
	void categoryNotFound () {
		when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
		
		try {
			categoryService.addProduct(1L, 1L);
		} catch (CategoryProblemException e) {
			assertEquals("Categoria com id 1 não foi encontrada", e.getMessage());
		}
	}
	
	@Test
	void addProduct_notFound () {
		
	when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockCategory));
	
	when(productRepository.findById(1L)).thenReturn(Optional.empty());
	
	try {
		categoryService.addProduct(1L, 1L); 
	} catch ( ProductProblemException e) {
	
		assertEquals("Produto com id 1 não foi encontrado", e.getMessage());
	}
	
	}
	
	@Test
	void addProductSucessful() {
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockCategory));
		
		when(productRepository.findById(1L)).thenReturn(Optional.of(productMock));
		
		categoryService.addProduct(1L, 1L);
		verify((categoryRepository), times(1)).save(mockCategory);
		verify((productRepository), times(1)).save(productMock);
	}

	





}
