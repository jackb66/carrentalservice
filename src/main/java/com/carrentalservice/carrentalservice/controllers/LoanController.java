package com.carrentalservice.carrentalservice.controllers;


import com.carrentalservice.carrentalservice.entities.Loan;
import com.carrentalservice.carrentalservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/create")
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        Loan createdLoan = loanService.createLoan(loan);
        return ResponseEntity.ok(createdLoan);
    }

    @PutMapping("/update")
    public ResponseEntity<Loan> updateLoan(@RequestParam Long id, @RequestBody Loan updatedLoan) {
        Loan updated = loanService.updateLoan(id, updatedLoan);
        return ResponseEntity.ok(updated);
    }
}

