package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {


}

