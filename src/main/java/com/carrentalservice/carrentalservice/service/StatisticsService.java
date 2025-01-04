package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.repositories.BranchRepository;
import com.carrentalservice.carrentalservice.repositories.CarRepository;
import com.carrentalservice.carrentalservice.repositories.ReservationRepository;
import com.carrentalservice.carrentalservice.repositories.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    private RevenueRepository revenueRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private CarRepository carRepository;

    // 1. Get revenue statistics by division
    public List<Map<String, Object>> getRevenueByDivision() {
        return revenueRepository.getRevenueByDivision();
    }

    // 2. Get revenue statistics by city
    public List<Map<String, Object>> getRevenueByCity() {
        return revenueRepository.getRevenueByCity();
    }

    // 3. Get revenue statistics by employee
    public List<Map<String, Object>> getRevenueByEmployee() {
        return revenueRepository.getRevenueByEmployee();
    }

    // 4. Get most popular routes
    public List<Map<String, Object>> getPopularRoutes() {
        return reservationRepository.getPopularRoutes();
    }

    // 5. Get most popular branches (by number of rentals)
    public List<Map<String, Object>> getPopularBranches() {
        return branchRepository.getPopularBranches();
    }

    // 6. Get most popular car models
    public List<Map<String, Object>> getPopularCars() {
        return carRepository.getPopularCars();
    }

    // 7. Get statistics on canceled reservations
    public List<Map<String, Object>> getCanceledReservationsStats() {
        return reservationRepository.getCanceledReservationsStats();
    }
}