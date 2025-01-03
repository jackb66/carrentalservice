package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // You can define custom queries if necessary, for example:
    Customer findByEmail(String email);

}

