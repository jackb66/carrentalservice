package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

