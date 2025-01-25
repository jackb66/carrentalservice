package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomer_Id(Long customerId);
    @Query("SELECT r FROM Reservation r WHERE r.loan.employee.id = :employeeId")
    List<Reservation> findReservationsByEmployeeId(@Param("employeeId") Long employeeId);
    List<Reservation> findByCarIdAndDateFromLessThanEqualAndDateToGreaterThanEqual
            (Long carId, LocalDate dateTo, LocalDate dateFrom);
    List<Reservation> findReservationsByCustomer_Email(String email);
}


