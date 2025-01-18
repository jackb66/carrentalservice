package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomerId(Long customerId);

    List<Reservation> findByCarId(Long carId);

    List<Reservation> findByBranchId(Long branchId);

    Optional<Reservation> findByCarIdAndDateFromAndDateTo(Long carId, LocalDate dateFrom, LocalDate dateTo);

    @Query("SELECT r.branchLoan.address AS pickupBranch, r.returnBranch.address AS returnBranch, COUNT(r) AS total " +
            "FROM Reservation r GROUP BY r.branchLoan, r.returnBranch ORDER BY COUNT(r) DESC")
    List<Map<String, Object>> getPopularRoutes();

    @Query("SELECT r.branchLoan.address AS branch, COUNT(r) AS totalRentals FROM Reservation r " +
            "GROUP BY r.branchLoan ORDER BY COUNT(r) DESC")
    List<Map<String, Object>> getPopularBranches();

    @Query("SELECT r.car.brand AS carBrand, r.car.model AS carModel, COUNT(r.car) AS totalRentals " +
            "FROM Reservation r GROUP BY r.car.brand, r.car.model ORDER BY COUNT(r.car) DESC")
    List<Map<String, Object>> getPopularCars();

    @Query("SELECT r.branchLoan.address AS branch, COUNT(r) AS totalCancellations FROM Reservation r " +
            "WHERE r.status = 'CANCELED' GROUP BY r.branchLoan ORDER BY COUNT(r) DESC")
    List<Map<String, Object>> getCanceledReservationsStats();
}


