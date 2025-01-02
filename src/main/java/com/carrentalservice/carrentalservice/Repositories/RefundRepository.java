package com.carrentalservice.carrentalservice.Repositories;

import com.carrentalservice.carrentalservice.Entities.Employee;
import com.carrentalservice.carrentalservice.Entities.Refund;
import com.carrentalservice.carrentalservice.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {

    List<Refund> findByEmployee(Employee employee);
    List<Refund> findByReservation(Reservation reservation);
}

