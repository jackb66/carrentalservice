package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BranchRepository extends JpaRepository <Branch, Long> {
}
