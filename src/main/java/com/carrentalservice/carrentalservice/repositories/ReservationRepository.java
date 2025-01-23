package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByCarIdAndDateFromLessThanEqualAndDateToGreaterThanEqual
            (Long carId, LocalDate dateTo, LocalDate dateFrom);

    Optional<Reservation> findById(Long id);

}



