package com.carrentalservice.carrentalservice.Repositories;

import com.carrentalservice.carrentalservice.Entities.Branch;
import com.carrentalservice.carrentalservice.Entities.Car;
import com.carrentalservice.carrentalservice.Static_Data.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByStatusAndStatusDateBefore(CarStatus status, Date statusDate);
    List<Car> findByBranch(Branch branch);
    List<Car> findByStatus(CarStatus status);
}

