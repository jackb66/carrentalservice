package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Rental;
import com.carrentalservice.carrentalservice.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
@Service
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
    public ResponseEntity<Rental> updateRental(@PathVariable Long id, @RequestBody Rental rentalDetails) {
        return this.getRentalById(id)
                .map(existingRental -> {
                    existingRental.setName(rentalDetails.getName());
                    existingRental.setInternetDomain(rentalDetails.getInternetDomain());
                    existingRental.setContactAddress(rentalDetails.getContactAddress());
                    existingRental.setOwner(rentalDetails.getOwner());
                    existingRental.setLogotype(rentalDetails.getLogotype());
                    return ResponseEntity.ok(this.saveRental(existingRental));
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
