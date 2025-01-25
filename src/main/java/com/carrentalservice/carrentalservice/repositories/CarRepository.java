package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.static_data.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.time.LocalDate;


public interface CarRepository extends JpaRepository<Car, Long> {
//    List<Car> findByBranchIdAndAvailableDateAndStatus(Long branchId, LocalDate date, String status);
    @Query(value = "select car from Car car " +
            " join Reservation r on r.car = car where car.branch.id = :branchId and car.status = :status" +
            " and r.dateTo <= :date")
    List<Car> findAvailableByBranch(Long branchId, CarStatus status, LocalDate date);
    List<Car> findAllByBranch_Id(Long branchId);
}

