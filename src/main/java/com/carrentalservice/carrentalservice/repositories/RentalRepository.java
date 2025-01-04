package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    Optional<Rental> findByName(String name);
    Optional<Rental> findByInternetDomain(String domain);
}
