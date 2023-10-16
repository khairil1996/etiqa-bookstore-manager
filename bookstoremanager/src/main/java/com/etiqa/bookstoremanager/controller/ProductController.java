package com.etiqa.bookstoremanager.controller;

import com.etiqa.bookstoremanager.entity.Customer;
import com.etiqa.bookstoremanager.entity.Product;
import com.etiqa.bookstoremanager.response.DeleteResponse;
import com.etiqa.bookstoremanager.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // POST - Create a new product
    @PostMapping("/create/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveOrUpdateProduct(product));
    }

    // GET - Get all products
    @Operation(summary = "Get all books", description = "Get all books in the store")
    @GetMapping("/get/all/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET - Get a single product by ID
    @GetMapping("/get/product/by/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT - Update a product
    @PutMapping("/update/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProductOpt = productService.getProductById(id);
        if (!existingProductOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Product  existingProduct = existingProductOpt.get();
        existingProduct.setBookTitle(product.getBookTitle());
        existingProduct.setBookPrice(product.getBookPrice());
        existingProduct.setBookQuantity(product.getBookQuantity());
        return ResponseEntity.ok(productService.saveOrUpdateProduct(existingProduct));
    }

    // DELETE - Delete a product by ID
    @DeleteMapping("/delete/product/{id}")
    public ResponseEntity<DeleteResponse> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("Book with ID: " + id + " is deleted.");
        deleteResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
    }
}

