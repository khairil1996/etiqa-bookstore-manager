package com.etiqa.bookstoremanager.service;

import com.etiqa.bookstoremanager.entity.Customer;
import com.etiqa.bookstoremanager.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);



    @Autowired
    private CustomerRepository customerRepository;

    // Fetch all customers
    public List<Customer> getAllCustomers() {
        logger.info("CustomerService getAllCustomers Start");
        return customerRepository.findAll();
    }

    // Fetch a single customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        logger.info("CustomerService getCustomerById Start");
        return customerRepository.findById(id);
    }

    // Save or update a customer
    public Customer saveOrUpdateCustomer(Customer customer) {
        logger.info("CustomerService saveOrUpdateCustomer Start");
        return customerRepository.save(customer);
    }

    // Delete a customer by ID
    public void deleteCustomer(Long id) {
        logger.info("CustomerService deleteCustomer Start");
        customerRepository.deleteById(id);
    }

}

