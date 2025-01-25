package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.entities.Employee;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping ("/create")
    public ResponseEntity<Employee> createEmployee(@RequestParam Employee employee, @RequestParam Long branchId) {
        return ResponseEntity.ok(employeeService.create(employee, branchId));
    }

    @PutMapping("/username")
    public ResponseEntity<Employee> updateEmployee(@RequestBody String username, @RequestBody Employee employee, @RequestParam Long branchId) {
        employee.setUsername(username);
        return ResponseEntity.ok(employeeService.update(employee, branchId));
    }

    @PatchMapping("/employeeId/deactivate")
    public ResponseEntity<Void> deactivateEmployee(@RequestParam Long employeeId) {
        employeeService.deactivateEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/branchId/employees")
    public ResponseEntity<List<Employee>> getEmployeesByBranch(@RequestParam Long branchId) {
        return ResponseEntity.ok(employeeService.getEmployeesByBranch(branchId));
    }

    @GetMapping("/employeeId/reservations")
    public ResponseEntity<List<Reservation>> getReservationsByEmployee(@RequestParam Long employeeId) {
        return ResponseEntity.ok(employeeService.getReservationsByEmployee(employeeId));
    }

    @GetMapping("/getAll/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/me")
    public ResponseEntity<Employee> getLoggedInEmployee() {
        return ResponseEntity.ok(employeeService.findLoggedInUser());
    }
}
