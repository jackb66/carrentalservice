package com.carrentalservice.carrentalservice.Services;

import com.carrentalservice.carrentalservice.Entities.Reservation;
import com.carrentalservice.carrentalservice.Entities.Revenue;
import com.carrentalservice.carrentalservice.Repositories.ReservationRepository;
import com.carrentalservice.carrentalservice.Repositories.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RevenueService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RevenueRepository revenueRepository;

    // Calculate total revenue for a specific period (e.g., day)
    public Revenue calculateRevenue(Date startDate, Date endDate) {
        List<Reservation> reservations = reservationRepository.findByDateRange(startDate, endDate);

        double totalAmount = reservations.stream()
                .mapToDouble(reservation -> reservation.getCar().getAmount() * (reservation.getDateTo().getTime() - reservation.getDateFrom().getTime()) / (1000 * 60 * 60 * 24))
                .sum();

        Revenue revenue = new Revenue();
        revenue.setDate(startDate);
        revenue.setTotalAmount(totalAmount);

        return revenueRepository.save(revenue);
    }
}

