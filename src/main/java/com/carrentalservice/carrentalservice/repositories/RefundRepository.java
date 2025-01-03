package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Employee;
import com.carrentalservice.carrentalservice.entities.Refund;
import com.carrentalservice.carrentalservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {

}

