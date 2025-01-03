package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Employee;
import com.carrentalservice.carrentalservice.entities.Loan;
import com.carrentalservice.carrentalservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByEmployee(Employee employee);
    List<Loan> findByReservation(Reservation reservation);
}

