package com.etiqa.bookstoremanager.controller;

import com.etiqa.bookstoremanager.entity.Customer;
import com.etiqa.bookstoremanager.entity.Product;
import com.etiqa.bookstoremanager.response.DeleteResponse;
import com.etiqa.bookstoremanager.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Create a new product", tags = {"product"})
    @ApiResponse(responseCode = "200", description = "Book created successfully")
    @PostMapping("/create/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveOrUpdateProduct(product));
    }

    // GET - Get all products
    @Operation(summary = "Get all products", tags = {"product"})
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/get/all/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET - Get a single product by ID
    @Operation(summary = "Get a product by ID", tags = {"product"})
    @ApiResponse(responseCode = "200", description = "Book found")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @GetMapping("/get/product/by/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT - Update a product
    @Operation(summary = "Update a product by ID", tags = {"product"})
    @ApiResponse(responseCode = "200", description = "Product updated successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
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
    @Operation(summary = "Delete a product by ID", tags = {"product"})
    @ApiResponse(responseCode = "200", description = "Product deleted successfully")
    @DeleteMapping("/delete/product/{id}")
    public ResponseEntity<DeleteResponse> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("Book with ID: " + id + " is deleted.");
        deleteResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
    }
}

