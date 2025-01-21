package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Refund;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.entities.Revenue;
import com.carrentalservice.carrentalservice.repositories.ReservationRepository;
import com.carrentalservice.carrentalservice.repositories.RevenueRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

@AllArgsConstructor

@Service
public class RevenueService {
    private final ReservationRepository reservationRepository;

//    // Helper method to calculate revenue from reservations
//    private Double calculateTotalRevenue(List<Reservation> reservations) {
//        return reservations.stream().mapToDouble(reservation -> {
//            double revenue = reservation.getAmount();
//            Refund refund = reservation.getRefund();
//            if (refund != null && refund.getSurcharge() != null) {
//                revenue += refund.getSurcharge();
//            }
//            return revenue;
//        }).sum();
//    }

    // Fitimi për vitin aktual për një branch të caktuar
    public Double calculateCurrentYearRevenueForBranch(Long branchId) {
        List<Reservation> reservations = reservationRepository.calculateCurrentYearRevenueForBranch(branchId);
        return reservations.stream()
                .mapToDouble(r -> r.getAmount() + (r.getRefund() != null ? r.getRefund().getSurcharge() : 0.0))
                .sum();
    }

    // Fitimi për një branch duke filluar nga një datë e caktuar
    public Double calculateRevenueForBranchFromDate(Long branchId, LocalDateTime startDate) {
        List<Reservation> reservations = reservationRepository.calculateRevenueForBranchFromDate(branchId, startDate);
        return reservations.stream()
                .mapToDouble(r -> r.getAmount() + (r.getRefund() != null ? r.getRefund().getSurcharge() : 0.0))
                .sum();
    }

    // Fitimi për një rental nga një datë deri në një datë
    public Double calculateRevenueForRentalBetweenDates(Long rentalId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Reservation> reservations = reservationRepository.calculateRevenueForRentalBetweenDates(rentalId, startDate, endDate);
        return reservations.stream()
                .mapToDouble(r -> r.getAmount() + (r.getRefund() != null ? r.getRefund().getSurcharge() : 0.0))
                .sum();

    }
}