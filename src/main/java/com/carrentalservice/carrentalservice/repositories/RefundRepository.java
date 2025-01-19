package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {

}
