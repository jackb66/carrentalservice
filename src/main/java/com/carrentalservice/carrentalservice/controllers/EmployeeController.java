package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.entities.Employee;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping ("/create/{branchId}")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, @PathVariable Long branchId) {
        return ResponseEntity.ok(employeeService.create(employee, branchId));
    }

    @PutMapping("/update/{branchId}")
    public ResponseEntity<Employee> updateEmployee( @RequestBody Employee employee, @PathVariable Long branchId) {
        return ResponseEntity.ok(employeeService.update(employee, branchId));
    }

    @PatchMapping("/deactivate")
    public ResponseEntity<Void> deactivateEmployee(@RequestParam Long employeeId) {
        employeeService.deactivateEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by_branch")
    public ResponseEntity<List<Employee>> getEmployeesByBranch(@RequestParam Long branchId) {
        return ResponseEntity.ok(employeeService.getEmployeesByBranch(branchId));
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservationsByEmployee(@RequestParam Long employeeId) {
        return ResponseEntity.ok(employeeService.getReservationsByEmployee(employeeId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/me")
    public ResponseEntity<Employee> getLoggedInEmployee() {
        return ResponseEntity.ok(employeeService.findLoggedInUser());
    }

    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password){
        return employeeService.login(username,password);
    }

}
