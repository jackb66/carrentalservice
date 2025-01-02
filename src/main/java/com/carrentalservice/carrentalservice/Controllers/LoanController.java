package com.carrentalservice.carrentalservice.Controllers;

import com.carrentalservice.carrentalservice.Entities.Employee;
import com.carrentalservice.carrentalservice.Entities.Loan;
import com.carrentalservice.carrentalservice.Entities.Reservation;
import com.carrentalservice.carrentalservice.Repositories.EmployeeRepository;
import com.carrentalservice.carrentalservice.Repositories.ReservationRepository;
import com.carrentalservice.carrentalservice.Services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping
    public Loan createLoan(@RequestParam Long employeeId,
                           @RequestParam Long reservationId,
                           @RequestParam String comments) {
        return loanService.createLoan(employeeId, reservationId, comments);
    }

    @GetMapping("/employee_Id")
    public List<Loan> getLoansByEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return loanService.getLoansByEmployee(employee);
    }

    @GetMapping("reservation_Id")
    public List<Loan> getLoansByReservation(@PathVariable Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        return loanService.getLoansByReservation(reservation);
    }
}

