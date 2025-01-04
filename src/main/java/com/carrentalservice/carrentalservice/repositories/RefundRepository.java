package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    Optional<Refund> findByReservationId(Long reservationId);
    List<Refund> findByEmployeeId(Long employeeId);
}
