package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomerId(Long customerId);

    @Query("SELECT r FROM Reservation r WHERE r.employee.id = :employeeId")
    List<Reservation> findReservationsByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT r FROM Reservation r WHERE r.customer.email = :email")
    List<Reservation> findReservationsByCustomerEmail(@Param("email") String email);

    List<Reservation> findByCarId(Long carId);

    @Query("SELECT r FROM Reservation r WHERE r.branchOfLoan.id = :branchId AND YEAR(r.bookingDate) = YEAR(CURRENT_DATE)")
    List<Reservation> calculateCurrentYearRevenueForBranch(@Param("branchId") Long branchId); //kthehet nje liste me rezervime dhe duhet te mbledh amount me refund.surcharge

    @Query("SELECT r FROM Reservation r WHERE r.branchOfLoan.id = :branchId AND r.bookingDate >= :startDate")
    List<Reservation> calculateRevenueForBranchFromDate(@Param("branchId") Long branchId, @Param("startDate") LocalDateTime startDate);

    @Query("SELECT r FROM Reservation r WHERE r.branchOfLoan.rental.id = :rentalId AND r.bookingDate BETWEEN :startDate AND :endDate")
    List<Reservation> calculateRevenueForRentalBetweenDates(@Param("rentalId") Long rentalId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}


