package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.dto.ReservationRequest;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest reservationRequest) {
        Reservation reservation = reservationService.createReservation(reservationRequest);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/availability")
    public ResponseEntity<Boolean> checkCarAvailability(
            @RequestParam Long carId,
            @RequestParam String dateFrom,
            @RequestParam String dateTo) {
        boolean isAvailable = reservationService.isCarAvailable(carId,
                LocalDate.parse(dateFrom),
                LocalDate.parse(dateTo));
        return ResponseEntity.ok(isAvailable);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Reservation> cancel(@RequestParam Long reservationId) {
        Reservation canceledReservation = reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok(canceledReservation);
    }
}
