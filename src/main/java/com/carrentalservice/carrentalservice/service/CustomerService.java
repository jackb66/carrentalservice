package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Customer;
import com.carrentalservice.carrentalservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer); // Save customer to the database
    }

    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email); // Find customer by email
    }

    // Optional: This can be used for customer login
    public boolean verifyCustomerEmail(String email, String password) {
        Optional<Customer> customer = findCustomerByEmail(email);
        // Implement password verification logic here
        return customer.isPresent(); // Just check if customer ex
    }
}