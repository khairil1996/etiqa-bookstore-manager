package com.etiqa.bookstoremanager.service;

import com.etiqa.bookstoremanager.entity.Product;
import com.etiqa.bookstoremanager.exception.ProductNotFoundException;
import com.etiqa.bookstoremanager.exception.ProductServiceException;
import com.etiqa.bookstoremanager.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllProductsTest() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(new Product(), new Product()));
        assertEquals(2, productService.getAllProducts().size());

        when(productRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(ProductServiceException.class, () -> productService.getAllProducts());
    }

    @Test
    public void getProductByIdTest() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));

        assertNotNull(productService.getProductById(productId));

        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(productId));

        when(productRepository.findById(productId)).thenThrow(new RuntimeException());
        assertThrows(ProductServiceException.class, () -> productService.getProductById(productId));
    }

    @Test
    public void saveOrUpdateProductTest() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        assertEquals(product, productService.saveOrUpdateProduct(product));

        when(productRepository.save(product)).thenThrow(new RuntimeException());
        assertThrows(ProductServiceException.class, () -> productService.saveOrUpdateProduct(product));
    }

    @Test
    public void deleteProductTest() {
        Long productId = 1L;
        when(productRepository.existsById(productId)).thenReturn(true);

        assertDoesNotThrow(() -> productService.deleteProduct(productId));

        when(productRepository.existsById(productId)).thenReturn(false);
        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(productId));

        when(productRepository.existsById(productId)).thenThrow(new RuntimeException());
        assertThrows(ProductServiceException.class, () -> productService.deleteProduct(productId));
    }
}

