package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.entities.Customer;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }

    @PutMapping("/customerId")
    public ResponseEntity<Customer> updateCustomer(@RequestParam Long customerId, @RequestParam Customer customer) {
        customer.setId(customerId);
        return ResponseEntity.ok(customerService.update(customer, null)); // Adjust branchId if necessary
    }

    @GetMapping("/email")
    public ResponseEntity<Optional<Customer>> getCustomerByEmail(@RequestBody String email) {
        return ResponseEntity.ok(customerService.findCustomerByEmail(email));
    }

    @GetMapping("/email/reservations")
    public ResponseEntity<List<Reservation>> getCustomerReservations(@RequestBody String email) {
        return ResponseEntity.ok(customerService.getReservationsByCustomerEmail(email));
    }

    @GetMapping("getAll/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/rental/rentalId")
    public ResponseEntity<List<Customer>> getCustomersByRentalId(@RequestBody Long rentalId) {
        return ResponseEntity.ok(customerService.findAllCustomersByRentalId(rentalId));
    }
}