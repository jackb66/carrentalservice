package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Branch;
import com.carrentalservice.carrentalservice.entities.Rental;
import com.carrentalservice.carrentalservice.repositories.BranchRepository;
import com.carrentalservice.carrentalservice.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private RentalRepository rentalRepository;

    // Create a new branch
    public Branch createBranch(Branch branch, Long rentalId) {
        //Gjeje rentalin me id

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found with id " + rentalId));
        branch.setRental(rental  );
        return branchRepository.save(branch);
    }

    public Branch updateBranch(Long id, Branch updatedBranch) {
        return null;
    }

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch getBranchById(Long id) {
        Optional<Branch> branch = branchRepository.findById(id);
        if (branch.isPresent()) {
            return branch.get();
        } else {
            throw new RuntimeException("Branch not found with id " + id);
        }
    }
}