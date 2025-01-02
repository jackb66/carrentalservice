package com.carrentalservice.carrentalservice.Repositories;

import com.carrentalservice.carrentalservice.Entities.Employee;
import com.carrentalservice.carrentalservice.Entities.Loan;
import com.carrentalservice.carrentalservice.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByEmployee(Employee employee);
    List<Loan> findByReservation(Reservation reservation);
}

