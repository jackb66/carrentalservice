package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.entities.Loan;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.repositories.CarRepository;
import com.carrentalservice.carrentalservice.repositories.LoanRepository;
import com.carrentalservice.carrentalservice.static_data.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private EmployeeService employeeService;

    public Loan createLoan(Loan loan) {
        loan.setDateOfRental(LocalDate.now());
        loan.setEmployee(employeeService.findLoggedInUser());
        Reservation reservation= reservationService.findById(loan.getReservation().getId());
        Car car = reservation.getCar();
        car.setStatus(CarStatus.BOOKED);
        loan.setReservation(reservation);
        return loanRepository.save(loan);
    }
    public Loan updateLoan(Long id, Loan updatedLoan) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (optionalLoan.isPresent()) {
            Loan existingLoan = optionalLoan.get();
            existingLoan.setEmployee(updatedLoan.getEmployee());
            existingLoan.setDateOfRental(updatedLoan.getDateOfRental());
            existingLoan.setReservation(updatedLoan.getReservation());
            existingLoan.setComments(updatedLoan.getComments());
            return loanRepository.save(existingLoan);
        } else {
            throw new RuntimeException("Loan with id " + id + "not found.");
        }
    }

}
