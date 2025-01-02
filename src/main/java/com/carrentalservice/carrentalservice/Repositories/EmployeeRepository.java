package com.carrentalservice.carrentalservice.Repositories;

import com.carrentalservice.carrentalservice.Entities.Branch;
import com.carrentalservice.carrentalservice.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // You can define custom queries here if needed, for example:
    List<Employee> findByPosition(Employee.Position position);
    List<Employee> findByBranch(Branch branch);
}

