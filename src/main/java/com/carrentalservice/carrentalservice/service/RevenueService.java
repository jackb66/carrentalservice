package com.carrentalservice.carrentalservice.service;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class RevenueService {

    private final ReservationRepository reservationRepository;

    // Fitimi për vitin aktual për një branch të caktuar
    public Double calculateCurrentYearRevenueForBranch(Long branchId) {
        List<Reservation> reservations = reservationRepository.calculateCurrentYearRevenueForBranch(branchId);
        return reservations.stream().mapToDouble(r -> r.getAmount() + (r.getRefund() != null ? r.getRefund().getSurcharge() : 0.0)).sum();
    }

    // Fitimi për një branch duke filluar nga një datë e caktuar
    public Double calculateRevenueForBranchFromDate(Long branchId, LocalDate startDate) {
        List<Reservation> reservations = reservationRepository.calculateRevenueForBranchFromDate(branchId, startDate);
        return reservations.stream().mapToDouble(r -> r.getAmount() + (r.getRefund() != null ? r.getRefund().getSurcharge() : 0.0)).sum();
    }

    // Fitimi për një rental nga një datë deri në një datë
    public Double calculateRevenueForRentalBetweenDates(Long rentalId, LocalDate startDate, LocalDate endDate) {
        List<Reservation> reservations = reservationRepository.calculateRevenueForRentalBetweenDates(rentalId, startDate, endDate);
        return reservations.stream().mapToDouble(r -> r.getAmount() + (r.getRefund() != null ? r.getRefund().getSurcharge() : 0.0)).sum();

    }
}