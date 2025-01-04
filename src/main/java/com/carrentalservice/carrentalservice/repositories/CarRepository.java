package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBranchId(Long branchId);
    List<Car> findByStatus(String status);
    List<Car> findByMakeAndModelAndYear(String make, String model, int year);
    List<Car> findByBranchIdAndStatus(Long branchId, String status);
    @Query("SELECT c.brand AS carBrand, c.model AS carModel, COUNT(r) AS totalRentals " +
            "FROM Reservation r JOIN r.car c GROUP BY c.brand, c.model ORDER BY COUNT(r) DESC")
    List<Map<String, Object>> getPopularCars();
}
