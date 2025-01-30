package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.entities.Revenue;
import com.carrentalservice.carrentalservice.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @GetMapping("/branch/{branchId}/current-year")
    public ResponseEntity<Revenue> getCurrentYearRevenueForBranch(
            @PathVariable Long branchId){
        Double revenue = revenueService.calculateCurrentYearRevenueForBranch(branchId);
        return ResponseEntity.ok(Revenue.builder()
                .totalAmount(revenue)
                .build());
    }

    @GetMapping("/branch/{branchId}/from-date")
    public ResponseEntity<Double> getRevenueForBranchFromDate(
            @PathVariable Long branchId,
            @RequestParam LocalDate startDate) {
        Double revenue = revenueService.calculateRevenueForBranchFromDate(branchId, startDate);
        return ResponseEntity.ok(revenue);
    }

    @GetMapping("/rental/{rentalId}/between-dates")
    public ResponseEntity<Revenue> getRevenueForRentalBetweenDates(
            @PathVariable Long rentalId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        Double revenue = revenueService.calculateRevenueForRentalBetweenDates(rentalId, startDate, endDate);
        return ResponseEntity.ok(Revenue.builder()
                        .startDate(startDate)
                        .endDate(endDate)
                        .totalAmount(revenue)
                .build());
    }
}
