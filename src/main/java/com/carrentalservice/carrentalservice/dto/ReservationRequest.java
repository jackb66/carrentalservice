package com.carrentalservice.carrentalservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationRequest {
    private String customerEmail;
    private Long carId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Long branchOfLoanId;
    private Long returnBranchId;
    private Double amount;
}
