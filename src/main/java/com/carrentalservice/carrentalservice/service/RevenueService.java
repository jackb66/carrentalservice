package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.entities.Revenue;
import com.carrentalservice.carrentalservice.repositories.ReservationRepository;
import com.carrentalservice.carrentalservice.repositories.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class RevenueService {

    @Autowired
    private RevenueRepository revenueRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    // Create revenue entry when a reservation is made
    @Transactional
    public Revenue recordReservationRevenue(Long reservationId, BigDecimal amount) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Revenue revenue = new Revenue(reservation, amount);
        return revenueRepository.save(revenue);
    }

    // Handle full cancellation (refund the full amount)
    @Transactional
    public Revenue handleFullCancellation(Long reservationId) {
        Revenue revenue = revenueRepository.findByReservationId(reservationId);
        if (revenue != null) {
            revenue.setApprovedAmount(revenue.getApprovedAmount().subtract(revenue.getApprovedAmount()));
            return revenueRepository.save(revenue);
        }
        throw new RuntimeException("Revenue entry not found for reservation ID: " + reservationId);
    }

    // Handle late cancellation (refund 80%)
    @Transactional
    public Revenue handleLateCancellation(Long reservationId) {
        Revenue revenue = revenueRepository.findByReservationId(reservationId);
        if (revenue != null) {
            BigDecimal refund = revenue.getApprovedAmount().multiply(BigDecimal.valueOf(0.8));
            revenue.setApprovedAmount(revenue.getApprovedAmount().subtract(refund));
            revenue.setUnapprovedAmount(revenue.getUnapprovedAmount().add(refund));
            return revenueRepository.save(revenue);
        }
        throw new RuntimeException("Revenue entry not found for reservation ID: " + reservationId);
    }

    // Handle surcharge on return (increase revenue)
    @Transactional
    public Revenue applySurcharge(Long reservationId, BigDecimal surcharge) {
        Revenue revenue = revenueRepository.findByReservationId(reservationId);
        if (revenue != null) {
            revenue.setApprovedAmount(revenue.getApprovedAmount().add(surcharge));
            return revenueRepository.save(revenue);
        }
        throw new RuntimeException("Revenue entry not found for reservation ID: " + reservationId);
    }
}