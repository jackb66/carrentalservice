package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.entities.Rental;
import com.carrentalservice.carrentalservice.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class RentalController {
    private final RentalService rentalService;
    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.saveRental(rental);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable Long id, @RequestBody Rental rentalDetails) {
        return rentalService.getRentalById(id)
                .map(existingRental -> {
                    existingRental.setName(rentalDetails.getName());
                    existingRental.setInternetDomain(rentalDetails.getInternetDomain());
                    existingRental.setContactAddress(rentalDetails.getContactAddress());
                    existingRental.setOwner(rentalDetails.getOwner());
                    existingRental.setLogotype(rentalDetails.getLogotype());
                    return ResponseEntity.ok(rentalService.saveRental(existingRental));
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
