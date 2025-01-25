package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.entities.Customer;
import com.carrentalservice.carrentalservice.entities.Rental;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
@Autowired
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.update(customer));
    }

    @GetMapping("/email")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email) {
        return ResponseEntity.ok(customerService.findCustomerByEmail(email));
    }

    @GetMapping("/email/reservations")
    public ResponseEntity<List<Reservation>> getCustomerReservations(@RequestParam String email) {
        return ResponseEntity.ok(customerService.getReservationsByCustomerEmail(email));
    }

    @GetMapping("getAll/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/by_rental")
    public ResponseEntity<List<Customer>> getCustomersByRentalId(@RequestParam Long rentalId) {
        return ResponseEntity.ok(customerService.findAllCustomersByRentalId(rentalId));
    }
}