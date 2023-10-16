package com.etiqa.bookstoremanager.service;

import com.etiqa.bookstoremanager.entity.Customer;
import com.etiqa.bookstoremanager.exception.CustomerNotFoundException;
import com.etiqa.bookstoremanager.exception.CustomerServiceException;
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

    public List<Customer> getAllCustomers() {
        logger.info("CustomerService getAllCustomers Start");
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while fetching all customers", e);
            throw new CustomerServiceException("Unable to fetch customers", e);
        }
    }

    public Optional<Customer> getCustomerById(Long id) {
        logger.info("CustomerService getCustomerById Start");
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (!customer.isPresent()) {
                throw new CustomerNotFoundException("Customer not found with id: " + id);
            }
            return customer;
        } catch (CustomerNotFoundException e) {
            throw e; // rethrowing to be handled by controller advice
        } catch (Exception e) {
            logger.error("Error occurred while fetching customer with id: " + id, e);
            throw new CustomerServiceException("Unable to fetch customer", e);
        }
    }

    public Customer saveOrUpdateCustomer(Customer customer) {
        logger.info("CustomerService saveOrUpdateCustomer Start");
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            logger.error("Error occurred while saving or updating customer", e);
            throw new CustomerServiceException("Unable to save or update customer", e);
        }
    }


    public void deleteCustomer(Long id) {
        logger.info("CustomerService deleteCustomer Start");
        try {
            if (!customerRepository.existsById(id)) {
                throw new CustomerNotFoundException("Customer not found with id: " + id);
            }
            customerRepository.deleteById(id);
        } catch (CustomerNotFoundException e) {
            throw e; // rethrowing to be handled by controller advice
        } catch (Exception e) {
            logger.error("Error occurred while deleting customer with id: " + id, e);
            throw new CustomerServiceException("Unable to delete customer", e);
        }
    }
}
