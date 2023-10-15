package com.etiqa.bookstoremanager.service;

import com.etiqa.bookstoremanager.entity.Product;
import com.etiqa.bookstoremanager.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    // Fetch all products
    public List<Product> getAllProducts() {
        logger.info("ProductService getAllProducts");
        return productRepository.findAll();
    }

    // Fetch a single product by ID
    public Optional<Product> getProductById(Long id) {
        logger.info("ProductService getProductById");
        return productRepository.findById(id);
    }

    // Save or update a product
    public Product saveOrUpdateProduct(Product product) {
        logger.info("ProductService saveOrUpdateProduct");
        return productRepository.save(product);
    }

    // Delete a product by ID
    public void deleteProduct(Long id) {
        logger.info("ProductService deleteProduct");
        productRepository.deleteById(id);
    }

}
