package com.carrentalservice.carrentalservice.Services;

import com.carrentalservice.carrentalservice.Entities.Employee;
import com.carrentalservice.carrentalservice.Entities.Loan;
import com.carrentalservice.carrentalservice.Entities.Reservation;
import com.carrentalservice.carrentalservice.Repositories.EmployeeRepository;
import com.carrentalservice.carrentalservice.Repositories.LoanRepository;
import com.carrentalservice.carrentalservice.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public Loan createLoan(Long employeeId, Long reservationId, String comments) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Loan loan = new Loan();
        loan.setEmployee(employee);
        loan.setReservation(reservation);
        loan.setDateOfRental(new Date());
        loan.setComments(comments);

        return loanRepository.save(loan);
    }


    public List<Loan> getLoansByEmployee(Employee employee) {
        return loanRepository.findByEmployee(employee);
    }


    public List<Loan> getLoansByReservation(Reservation reservation) {
        return loanRepository.findByReservation(reservation);
    }
}

