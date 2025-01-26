package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @GetMapping("/branch/{branchId}/current-year")
    public ResponseEntity<Double> getCurrentYearRevenueForBranch(@PathVariable Long branchId) {
        Double revenue = revenueService.calculateCurrentYearRevenueForBranch(branchId);
        return ResponseEntity.ok(revenue);
    }

    @GetMapping("/branch/{branchId}/from-date")
    public ResponseEntity<Double> getRevenueForBranchFromDate(
            @PathVariable Long branchId,
            @RequestParam String startDate) {
        LocalDateTime parsedStartDate = LocalDateTime.parse(startDate);
        Double revenue = revenueService.calculateRevenueForBranchFromDate(branchId, parsedStartDate);
        return ResponseEntity.ok(revenue);
    }

    @GetMapping("/rental/{rentalId}/between-dates")
    public ResponseEntity<Double> getRevenueForRentalBetweenDates(
            @PathVariable Long rentalId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDateTime parsedStartDate = LocalDateTime.parse(startDate);
        LocalDateTime parsedEndDate = LocalDateTime.parse(endDate);
        Double revenue = revenueService.calculateRevenueForRentalBetweenDates(rentalId, parsedStartDate, parsedEndDate);
        return ResponseEntity.ok(revenue);
    }
}
