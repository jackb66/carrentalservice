package com.carrentalservice.carrentalservice.Repositories;

import com.carrentalservice.carrentalservice.Entities.Customer;
import com.carrentalservice.carrentalservice.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // You can define custom queries if necessary, for example:
    Customer findByEmail(String email);

}

