package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Customer;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.repositories.CustomerRepository;
import com.carrentalservice.carrentalservice.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer, Long branchId) {
        Customer existingCustomer = customerRepository.findById(customer.getId())
                .orElseThrow();
        if (customer.getFirstName() != null)
            existingCustomer.setFirstName(customer.getFirstName());
        if (customer.getLastName() != null)
            existingCustomer.setLastName(existingCustomer.getLastName());
        if (customer.getAddress() != null)
            existingCustomer.setAddress(existingCustomer.getAddress());
        if (customer.getReservations() != null)
            existingCustomer.setReservations(customer.getReservations());
        return customerRepository.save(existingCustomer);
    }

    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);

    }

    public List<Reservation> getReservationsByCustomerEmail(String email) {
        return reservationRepository.findReservationsByCustomerEmail(email);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

        public List<Customer> findAllCustomersByRentalId(Long rentalId) {
            return customerRepository.findAllCustomersByRentalId(rentalId);

    }
}
