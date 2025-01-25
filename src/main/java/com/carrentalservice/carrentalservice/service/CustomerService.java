package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Customer;
import com.carrentalservice.carrentalservice.entities.Employee;
import com.carrentalservice.carrentalservice.entities.Rental;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.repositories.CustomerRepository;
import com.carrentalservice.carrentalservice.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final EmployeeService employeeService;

    public Customer create(Customer customer) {
        Employee employee = employeeService.findLoggedInUser();
        Rental rental = employee.getBranch().getRental();
        customer.setRental(rental);
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        Customer existingCustomer = customerRepository.findById(customer.getId())
                .orElseThrow();
        if (customer.getFirstName() != null)
            existingCustomer.setFirstName(customer.getFirstName());
        if (customer.getLastName() != null)
            existingCustomer.setLastName(existingCustomer.getLastName());
        if (customer.getAddress() != null)
            existingCustomer.setAddress(existingCustomer.getAddress());
        return customerRepository.save(existingCustomer);
    }

    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElseThrow();

    }

    public List<Reservation> getReservationsByCustomerEmail(String email) {
        return reservationRepository.findReservationsByCustomerEmail(email);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

        public List<Customer> findAllCustomersByRentalId(Long rentalId) {
            return customerRepository.findAllByRental_Id(rentalId);

    }
}
