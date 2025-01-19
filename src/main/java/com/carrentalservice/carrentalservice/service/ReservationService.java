package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.entities.Customer;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.repositories.CarRepository;
import com.carrentalservice.carrentalservice.repositories.CustomerRepository;
import com.carrentalservice.carrentalservice.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public Reservation createReservation(Long carId, Long customerId, LocalDate dateFrom, LocalDate dateTo, Double amount) {

        List<Reservation> conflictingReservations = reservationRepository
                .findByCarIdAndDateFromAndDateTo(carId, dateFrom, dateTo);

        if (!conflictingReservations.isEmpty()) {
            throw new RuntimeException("Car is already reserved for the selected dates.");
        }

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Reservation reservation = Reservation.builder()
                .car(car)
                .customer(customer)
                .dateFrom(dateFrom)
                .dateTo(dateTo)
                .amount(amount)
                .bookingDate(LocalDateTime.now())
                .build();

        return reservationRepository.save(reservation);
    }
}
