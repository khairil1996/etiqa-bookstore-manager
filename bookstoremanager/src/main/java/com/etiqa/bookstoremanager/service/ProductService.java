package com.etiqa.bookstoremanager.service;

import com.etiqa.bookstoremanager.entity.Product;
import com.etiqa.bookstoremanager.exception.ProductNotFoundException;
import com.etiqa.bookstoremanager.exception.ProductServiceException;
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

    public List<Product> getAllProducts() {
        logger.info("ProductService getAllProducts Start");
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while fetching all products", e);
            throw new ProductServiceException("Unable to fetch products", e);
        }
    }

    public Optional<Product> getProductById(Long id) {
        logger.info("ProductService getProductById Start for product ID: {}", id);
        try {
            Optional<Product> product = productRepository.findById(id);
            if (!product.isPresent()) {
                throw new ProductNotFoundException("Product not found with id: " + id);
            }
            return product;
        } catch (ProductNotFoundException e) {
            throw e; // rethrowing to be handled by controller advice
        } catch (Exception e) {
            logger.error("Error occurred while fetching product with id: " + id, e);
            throw new ProductServiceException("Unable to fetch product", e);
        }
    }

    public Product saveOrUpdateProduct(Product product) {
        logger.info("ProductService saveOrUpdateProduct Start");
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            logger.error("Error occurred while saving or updating product", e);
            throw new ProductServiceException("Unable to save or update product", e);
        }
    }

    public void deleteProduct(Long id) {
        logger.info("ProductService deleteProduct Start for product ID: {}", id);
        try {
            if (!productRepository.existsById(id)) {
                throw new ProductNotFoundException("Product not found with id: " + id);
            }
            productRepository.deleteById(id);
        } catch (ProductNotFoundException e) {
            throw e; // rethrowing to be handled by controller advice
        } catch (Exception e) {
            logger.error("Error occurred while deleting product with id: " + id, e);
            throw new ProductServiceException("Unable to delete product", e);
        }
    }
}
