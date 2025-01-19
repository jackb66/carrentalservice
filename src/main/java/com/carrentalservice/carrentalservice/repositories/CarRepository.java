package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.time.LocalDate;
import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBranchIdAndAvailableDateAndStatus(Long branchId, LocalDate date, String status);
    List<Car> findAll(String title);
    @Query(value = "select car from Car car where car.branch.id = :branchId")
    List<Car> findByBranchId(Long branchId);
}

