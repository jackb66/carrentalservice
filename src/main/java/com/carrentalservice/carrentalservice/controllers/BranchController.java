package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.entities.Branch;
import com.carrentalservice.carrentalservice.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {

@Autowired
    private BranchService branchService;


    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @PostMapping("/create/{rentalId}")
    public Branch createBranch(@RequestBody Branch branch, @PathVariable Long rentalId) {
        return branchService.createBranch(branch, rentalId);
    }


    @GetMapping("/{id}")
    public Branch getBranchById(@PathVariable Long id) {
        return branchService.getBranchById(id);
    }


    @PutMapping("/{id}")
    public Branch updateBranch(@PathVariable Long id, @RequestBody Branch branch) {
        return branchService.updateBranch(id, branch);
    }
}