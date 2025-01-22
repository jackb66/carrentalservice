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

//    public List<Map<String, Object>> getRevenueByDivision() {
//        return revenueRepository.getRevenueByDivision();
//    }
//
//    public List<Map<String, Object>> getRevenueByCity() {
//        return revenueRepository.getRevenueByCity();
//    }
//
//    public List<Map<String, Object>> getRevenueByEmployee() {
//        return revenueRepository.getRevenueByEmployee();
//    }
//
//    public List<Map<String, Object>> getPopularRoutes() {
//        return reservationRepository.getPopularRoutes();
//    }
//
//    public List<Map<String, Object>> getPopularBranches() {
//        return branchRepository.getPopularBranches();
//    }
//
////    public List<Map<String, Object>> getPopularCars() {
////        return carRepository.getPopularCars();
////    }
//
//    public List<Map<String, Object>> getCanceledReservationsStats() {
//        return reservationRepository.getCanceledReservationsStats();
//    }
}