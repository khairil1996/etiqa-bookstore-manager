package com.etiqa.bookstoremanager.controller;


import com.etiqa.bookstoremanager.entity.Customer;
import com.etiqa.bookstoremanager.response.DeleteResponse;
import com.etiqa.bookstoremanager.service.CustomerService;
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
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // POST - Create a new customer
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.saveOrUpdateCustomer(customer));
    }

    // GET - Get all customers
    @Operation(summary = "Get all customers", tags = {"customer"})
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/get/all/customer")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    // GET - Get a single customer by ID
    @GetMapping("/get/customer/by/id/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT - Update a customer
    @PutMapping("/update/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> existingCustomerOpt = customerService.getCustomerById(id);
        if (!existingCustomerOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Customer existingCustomer = existingCustomerOpt.get();
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setFamilyMembers(customer.getFamilyMembers());

        return ResponseEntity.ok(customerService.saveOrUpdateCustomer(existingCustomer));
    }


    // DELETE - Delete a customer by ID
    @DeleteMapping("/delete/customer/{id}")
    public ResponseEntity<DeleteResponse> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("User with ID: "+id+" is deleted.");
        deleteResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
    }
}
