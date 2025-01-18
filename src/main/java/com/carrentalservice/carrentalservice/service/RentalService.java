package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Employee;
import com.carrentalservice.carrentalservice.entities.Loan;
import com.carrentalservice.carrentalservice.entities.Rental;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.repositories.EmployeeRepository;
import com.carrentalservice.carrentalservice.repositories.LoanRepository;
import com.carrentalservice.carrentalservice.repositories.RentalRepository;
import com.carrentalservice.carrentalservice.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Loan rentCar(Long reservationId, Long employeeId, String comments) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));


        if (reservation.getStartDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("Reservation start date has passed or is invalid.");
        }

        Loan loan = new Loan();
        loan.setReservation(reservation);
        loan.setEmployee(employee);
        loan.setRentalDate(LocalDate.now());
        loan.setComments(comments);

        // Save the Loan to the database
        return loanRepository.save(loan);
    }

    public Rental createRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Rental getRentalById(Long id) {
        // Find the rental by its ID, if not found throw an exception or handle it
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isPresent()) {
            return rental.get();
        } else {
            throw new RuntimeException("Rental not found with id " + id);
        }
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental updateRental(Long id, Rental updatedRental) {
        Optional<Rental> existingRental = rentalRepository.findById(id);

        if (existingRental.isPresent()) {

            Rental rental = existingRental.get();


            rental.setName(updatedRental.getName());
            rental.setOwner(updatedRental.getOwner());
            rental.setLogotype(updatedRental.getLogotype());


            return rentalRepository.save(rental);
        } else {
            throw new RuntimeException("Rental not found with id " + id);
        }
    }

    public void deleteRental(Long id) {
        if (rentalRepository.existsById(id)) {
            rentalRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rental not found with id " + id);
        }
    }
}


