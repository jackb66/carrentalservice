package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Customer;
import com.carrentalservice.carrentalservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

//metode qe t ktheje te gjithe klientet e nje rentali
@Query("SELECT DISTINCT r.customer FROM Reservation r " +
            "JOIN r.branchOfLoan b " +
            "JOIN b.rental rental " +
            "WHERE rental.id = :rentalId")
    List<Customer> findAllCustomersByRentalId(@Param("rentalId") Long rentalId);

}
