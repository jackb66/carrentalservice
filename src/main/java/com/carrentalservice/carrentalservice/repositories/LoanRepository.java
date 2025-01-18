package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByReservationId(Long reservationId);
    List<Loan> findByEmployeeId(Long employeeId);
}
