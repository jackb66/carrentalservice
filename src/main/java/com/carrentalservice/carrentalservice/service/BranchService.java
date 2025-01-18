package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Branch;
import com.carrentalservice.carrentalservice.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    // Create a new branch
    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    // Update an existing branch
    public Branch updateBranch(Long id, Branch updatedBranch) {
        // Find the branch by ID
        Optional<Branch> existingBranch = branchRepository.findById(id);

        if (existingBranch.isPresent()) {
            Branch branch = existingBranch.get();
            // Update fields as needed
            branch.setAddress(updatedBranch.getAddress());
            branch.setCity(updatedBranch.getCity());
            // Save the updated branch back to the database
            return branchRepository.save(branch);
        } else {
            // Handle branch not found
            throw new RuntimeException("Branch not found with id " + id);
        }
    }

    // Retrieve all branches
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    // Retrieve a branch by ID
    public Branch getBranchById(Long id) {
        Optional<Branch> branch = branchRepository.findById(id);
        if (branch.isPresent()) {
            return branch.get();
        } else {
            throw new RuntimeException("Branch not found with id " + id);
        }
    }

    // Delete a branch by ID
    public void deleteBranch(Long id) {
        // Check if branch exists before attempting to delete
        if (branchRepository.existsById(id)) {
            branchRepository.deleteById(id);
        } else {
            throw new RuntimeException("Branch not found with id " + id);
        }
    }
}