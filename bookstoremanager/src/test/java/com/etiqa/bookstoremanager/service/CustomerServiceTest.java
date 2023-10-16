package com.etiqa.bookstoremanager.service;

import com.etiqa.bookstoremanager.entity.Customer;
import com.etiqa.bookstoremanager.exception.CustomerNotFoundException;
import com.etiqa.bookstoremanager.exception.CustomerServiceException;
import com.etiqa.bookstoremanager.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllCustomersTest() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(new Customer(), new Customer()));
        assertEquals(2, customerService.getAllCustomers().size());

        when(customerRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(CustomerServiceException.class, () -> customerService.getAllCustomers());
    }

    @Test
    public void getCustomerByIdTest() {
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(new Customer()));

        assertNotNull(customerService.getCustomerById(customerId));

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(customerId));

        when(customerRepository.findById(customerId)).thenThrow(new RuntimeException());
        assertThrows(CustomerServiceException.class, () -> customerService.getCustomerById(customerId));
    }

    @Test
    public void saveOrUpdateCustomerTest() {
        Customer customer = new Customer();
        when(customerRepository.save(customer)).thenReturn(customer);

        assertEquals(customer, customerService.saveOrUpdateCustomer(customer));

        when(customerRepository.save(customer)).thenThrow(new RuntimeException());
        assertThrows(CustomerServiceException.class, () -> customerService.saveOrUpdateCustomer(customer));
    }

    @Test
    public void deleteCustomerTest() {
        Long customerId = 1L;
        when(customerRepository.existsById(customerId)).thenReturn(true);

        assertDoesNotThrow(() -> customerService.deleteCustomer(customerId));

        when(customerRepository.existsById(customerId)).thenReturn(false);
        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer(customerId));

        when(customerRepository.existsById(customerId)).thenThrow(new RuntimeException());
        assertThrows(CustomerServiceException.class, () -> customerService.deleteCustomer(customerId));
    }
}
