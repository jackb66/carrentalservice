package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

}
