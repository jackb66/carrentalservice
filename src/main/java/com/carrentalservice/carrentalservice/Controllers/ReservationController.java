package com.carrentalservice.carrentalservice.Controllers;

import com.carrentalservice.carrentalservice.Entities.Car;
import com.carrentalservice.carrentalservice.Entities.Customer;
import com.carrentalservice.carrentalservice.Entities.Reservation;
import com.carrentalservice.carrentalservice.Repositories.CarRepository;
import com.carrentalservice.carrentalservice.Repositories.CustomerRepository;
import com.carrentalservice.carrentalservice.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
   @Autowired
   private CustomerRepository customerRepository;
   @Autowired
   private CarRepository carRepository;

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @GetMapping("/By_customer")
    public List<Reservation> getReservationsByCustomer(@PathVariable Long customerId) {
        Customer customer =  customerRepository.findById(customerId).orElseThrow(() ->
                new RuntimeException("Customer not found"));
        return reservationService.getReservationsByCustomer(customer);
    }

    @GetMapping("/by_car")
    public List<Reservation> getReservationsByCar(@PathVariable Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        return reservationService.getReservationsByCar(car);
    }
}

