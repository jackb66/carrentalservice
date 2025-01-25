package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository <Branch, Long> {
    List<Branch> findByActiveTrue();
    List<Branch> findByRental_Id(Long rentalId);
}
