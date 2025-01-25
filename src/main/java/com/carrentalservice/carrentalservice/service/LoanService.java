package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Loan;
import com.carrentalservice.carrentalservice.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Loan createLoan(Loan loan) {
        loan.setDateOfRental(LocalDate.now());
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
            throw new RuntimeException("Loan with id " + id + " not found.");
        }
    }
}
