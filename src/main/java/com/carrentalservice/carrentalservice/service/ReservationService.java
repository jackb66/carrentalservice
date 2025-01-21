package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Branch;
import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.entities.Customer;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.repositories.BranchRepository;
import com.carrentalservice.carrentalservice.repositories.CarRepository;
import com.carrentalservice.carrentalservice.repositories.CustomerRepository;
import com.carrentalservice.carrentalservice.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
}
