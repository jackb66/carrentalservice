package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAll(String title);
}
