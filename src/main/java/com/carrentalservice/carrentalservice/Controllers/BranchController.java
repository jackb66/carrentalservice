package com.carrentalservice.carrentalservice.Controllers;

import com.carrentalservice.carrentalservice.Entities.Branch;
import com.carrentalservice.carrentalservice.Services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    // Get all branches
    @GetMapping("all_branches")
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }


    @GetMapping("/by_id}")
    public Branch getBranchById(@PathVariable Long id) {
        return branchService.getBranchById(id);
    }
}

