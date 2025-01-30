package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.loan.employee.id = :employeeId")
    List<Reservation> findReservationsByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT r FROM Reservation r WHERE r.customer.email = :email")
    List<Reservation> findReservationsByCustomerEmail(@Param("email") String email);

    @Query("SELECT r FROM Reservation r WHERE r.branchOfLoan.id = :branchId AND YEAR(r.dateFrom) = YEAR(CURRENT_DATE)")
    List<Reservation> calculateCurrentYearRevenueForBranch(@Param("branchId") Long branchId);

    @Query("SELECT r FROM Reservation r WHERE r.branchOfLoan.id = :branchId AND r.dateFrom >= :startDate")
    List<Reservation> calculateRevenueForBranchFromDate(@Param("branchId") Long branchId, @Param("startDate") LocalDate startDate);

    @Query("SELECT r FROM Reservation r WHERE r.branchOfLoan.rental.id = :rentalId AND r.dateFrom BETWEEN :startDate AND :endDate")
    List<Reservation> calculateRevenueForRentalBetweenDates(@Param("rentalId") Long rentalId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Reservation> findByCarIdAndDateFromLessThanEqualAndDateToGreaterThanEqual
            (Long carId, LocalDate dateTo, LocalDate dateFrom);
}


