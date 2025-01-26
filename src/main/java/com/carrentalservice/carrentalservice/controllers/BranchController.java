package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.entities.Branch;
import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping ("/branchs")
public class BranchController {
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        Branch branch = branchService.getBranchById(id);
        return ResponseEntity.ok(branch);
    }
    @PostMapping("/{rentalId}")
    public ResponseEntity<Branch> createBranch(@RequestBody Branch branch, @PathVariable Long rentalId) {
        Branch createdBranch = branchService.createBranch(branch, rentalId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBranch);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable Long id, @RequestBody Branch updatedBranch) {
        Branch updated = branchService.updateBranch(id, updatedBranch);
        return ResponseEntity.ok(updated);
    }
    @PutMapping("/{id}/close")
    public ResponseEntity<Branch> closeBranch(@PathVariable Long id) {
        Branch closedBranch = branchService.closeBranch(id);
        return ResponseEntity.ok(closedBranch);
    }
    @GetMapping("/{branchId}/available-cars")
    public ResponseEntity<List<Car>> getAvailableCars(@PathVariable Long branchId, @RequestParam LocalDate fromDate) {
        List<Car> availableCars = branchService.getAvailableCars(branchId, fromDate);
        return ResponseEntity.ok(availableCars);
    }

}
