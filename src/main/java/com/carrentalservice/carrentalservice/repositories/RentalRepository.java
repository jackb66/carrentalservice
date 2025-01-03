package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {

}

