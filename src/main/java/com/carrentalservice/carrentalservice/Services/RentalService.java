package com.carrentalservice.carrentalservice.Services;

import com.carrentalservice.carrentalservice.Entities.Branch;
import com.carrentalservice.carrentalservice.Entities.Rental;
import com.carrentalservice.carrentalservice.Repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public Rental createRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    // Create a new rental network
    public Rental createRental(String name, String internetDomain, String contactAddress, String owner, String logotype) {
        Rental rental = new Rental();
        rental.setName(name);
        rental.setInternetDomain(internetDomain);
        rental.setContactAddress(contactAddress);
        rental.setOwner(owner);
        rental.setLogotype(logotype);

        return rentalRepository.save(rental);
    }

    // Add a branch to a rental network
    public void addBranchToRental(Long rentalId, Branch branch) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
        rental.addBranch(branch);
        rentalRepository.save(rental);
    }

    // Remove a branch from a rental network
    public void removeBranchFromRental(Long rentalId, Long branchId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        Branch branch = rental.getBranches().stream()
                .filter(b -> b.getId().equals(branchId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        rental.removeBranch(branch);
        rentalRepository.save(rental);
    }
}
