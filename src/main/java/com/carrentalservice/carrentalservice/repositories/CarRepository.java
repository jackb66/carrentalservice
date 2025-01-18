package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {
}
