package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Branch;
import com.carrentalservice.carrentalservice.entities.Customer;
import com.carrentalservice.carrentalservice.entities.Employee;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.repositories.CustomerRepository;
import com.carrentalservice.carrentalservice.repositories.EmployeeRepository;
import com.carrentalservice.carrentalservice.repositories.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Builder
@Service
@RequiredArgsConstructor
public class EmployeeService {
    @Autowired
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final BranchService branchService;
    private final ReservationRepository reservationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Employee> getEmployeesByBranch(Long branchId) {
        return employeeRepository.findByBranch_Id(branchId);
    }

    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username).orElseThrow();
    }

    public Employee create(Employee employee, Long branchId) {
        Branch branch = branchService.getBranchById(branchId);
        employee.setBranch(branch);
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setIsActive(true);
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee, Long branchId) {
        Employee existingEmployee = employeeRepository.findByUsername(employee.getUsername()).orElseThrow();
        Branch branch = branchService.getBranchById(branchId);
        existingEmployee.setBranch(branch);
        if (employee.getPassword() != null) existingEmployee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        if (employee.getFirstname() != null) existingEmployee.setFirstname(employee.getFirstname());
        if (employee.getPosition() != null) existingEmployee.setPosition(employee.getPosition());
        if (employee.getLastname() != null) existingEmployee.setLastname(employee.getLastname());
        if (employee.getBranch() != null) existingEmployee.setBranch(employee.getBranch());
        return employeeRepository.save(existingEmployee);

    }

    public void deactivateEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        employee.setIsActive(false);
        employeeRepository.save(employee);
    }

    public List<Reservation> getReservationsByEmployee(Long employeeId) {
        return reservationRepository.findReservationsByEmployeeId(employeeId);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();

    }

    public Employee findLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return employeeRepository.findByUsername(username).orElseThrow();
    }
}