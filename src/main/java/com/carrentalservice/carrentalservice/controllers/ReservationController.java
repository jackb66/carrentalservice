package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(
            @RequestParam Long carId,
            @RequestParam Long customerId,
            @RequestParam LocalDate dateFrom,
            @RequestParam LocalDate dateTo,
            @RequestParam Double amount) {
        Reservation reservation = reservationService.createReservation(carId, customerId, dateFrom, dateTo, amount);
        return ResponseEntity.ok(reservation);
    }
}
