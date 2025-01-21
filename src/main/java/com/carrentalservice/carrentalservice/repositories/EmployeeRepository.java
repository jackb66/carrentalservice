package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByBranchId(Long branchId);
    Optional<Employee> findByUsername(String username);


}
