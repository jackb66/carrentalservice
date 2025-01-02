package com.carrentalservice.carrentalservice.Repositories;

import com.carrentalservice.carrentalservice.Entities.Branch;
import com.carrentalservice.carrentalservice.Entities.Car;
import com.carrentalservice.carrentalservice.Entities.Customer;
import com.carrentalservice.carrentalservice.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Custom queries can be added here, for example:
        List<Reservation> findByClient(Customer client);
        List<Reservation> findByCar(Car car);
        List<Reservation> findByBranchOfLoan(Branch branch);
        List<Reservation> findByDateRange(Date startDate, Date endDate);



}



