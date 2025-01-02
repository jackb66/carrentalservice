package com.carrentalservice.carrentalservice.Services;

import com.carrentalservice.carrentalservice.Entities.Branch;
import com.carrentalservice.carrentalservice.Entities.Employee;
import com.carrentalservice.carrentalservice.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all employees by position
    public List<Employee> getEmployeesByPosition(Employee.Position position) {
        return employeeRepository.findByPosition(position);
    }

    // Get all employees by branch
    public List<Employee> getEmployeesByBranch(Branch branch) {
        return employeeRepository.findByBranch(branch);
    }

    // Create a new employee
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
