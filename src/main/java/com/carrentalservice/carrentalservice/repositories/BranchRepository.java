package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BranchRepository extends JpaRepository <Branch, Long> {

    List<Branch> findByCity(String city);

    @Query ("SELECT b.address AS branch, COUNT(r) AS totalRentals FROM Reservation r " +
            "JOIN r.branchLoan b GROUP BY b.address ORDER BY COUNT(r) DESC")
    List<Map<String, Object>> getPopularBranches();
    
}
