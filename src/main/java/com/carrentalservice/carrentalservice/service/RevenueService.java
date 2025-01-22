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

//    public Double getBranchRevenue(Long branchId) {
//        Double revenue = revenueRepository.calculateBranchRevenue(branchId);
//        return revenue != null ? revenue : 0.0;
//    }
}