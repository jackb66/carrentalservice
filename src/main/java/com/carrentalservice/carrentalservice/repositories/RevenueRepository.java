package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
    @Query("SELECT SUM(r.amount) FROM Revenue r")
    Double calculateTotalRevenue();
    List<Revenue> findByBranchId(Long branchId);
    List<Revenue> findByEmployeeId(Long employeeId);

    Revenue findByReservationId(Long reservationId);

    @Query("SELECT r.reservation.branchLoan.address AS division, SUM(r.approvedAmount) AS totalRevenue " +
            "FROM Revenue r GROUP BY r.reservation.branchLoan.address")
    List<Map<String, Object>> getRevenueByDivision();

    @Query("SELECT r.reservation.branchLoan.city AS city, SUM(r.approvedAmount) AS totalRevenue " +
            "FROM Revenue r GROUP BY r.reservation.branchLoan.city")
    List<Map<String, Object>> getRevenueByCity();

    @Query("SELECT r.reservation.employee.firstName AS employee, r.reservation.employee.lastName AS lastName, " +
            "SUM(r.approvedAmount) AS totalRevenue FROM Revenue r GROUP BY r.reservation.employee")
    List<Map<String, Object>> getRevenueByEmployee();

}

