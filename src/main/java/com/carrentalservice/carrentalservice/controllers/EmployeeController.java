package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.entities.Employee;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, @RequestParam Long branchId) {
        return ResponseEntity.ok(employeeService.create(employee, branchId));
    }

    @PutMapping("/{username}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String username, @RequestBody Employee employee, @RequestParam Long branchId) {
        employee.setUsername(username);
        return ResponseEntity.ok(employeeService.update(employee, branchId));
    }

    @PatchMapping("/{employeeId}/deactivate")
    public ResponseEntity<Void> deactivateEmployee(@PathVariable Long employeeId) {
        employeeService.deactivateEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{branchId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByBranch(@PathVariable Long branchId) {
        return ResponseEntity.ok(employeeService.getEmployeesByBranch(branchId));
    }

    @GetMapping("/{employeeId}/reservations")
    public ResponseEntity<List<Reservation>> getReservationsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.getReservationsByEmployee(employeeId));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/me")
    public ResponseEntity<Employee> getLoggedInEmployee() {
        return ResponseEntity.ok(employeeService.findLoggedInUser());
    }
}
