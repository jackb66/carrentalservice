package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BranchRepository extends JpaRepository <Branch, Long> {
}
