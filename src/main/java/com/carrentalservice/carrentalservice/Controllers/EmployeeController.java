package com.carrentalservice.carrentalservice.Controllers;

import com.carrentalservice.carrentalservice.Entities.Branch;
import com.carrentalservice.carrentalservice.Entities.Employee;
import com.carrentalservice.carrentalservice.Services.BranchService;
import com.carrentalservice.carrentalservice.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private BranchService branchService;

    // Get employees by position (EMPLOYEE or MANAGER)
    @GetMapping("/by_position")
    public List<Employee> getEmployeesByPosition(@PathVariable Employee.Position position) {
        return employeeService.getEmployeesByPosition(position);
    }

    @GetMapping("/branch_id")
    public List<Employee> getEmployeesByBranch(@PathVariable Long branchId) {
        Branch branch = branchService.getBranchById(branchId);  // Assuming branchService is implemented
        return employeeService.getEmployeesByBranch(branch);
    }

    @PostMapping("create")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }
}

