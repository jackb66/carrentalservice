package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Rental;
import com.carrentalservice.carrentalservice.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RentalService {

    private final RentalRepository rentalRepository;
    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }
    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

}
