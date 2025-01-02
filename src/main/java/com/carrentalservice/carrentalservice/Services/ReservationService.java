package com.carrentalservice.carrentalservice.Services;

import com.carrentalservice.carrentalservice.Entities.Car;
import com.carrentalservice.carrentalservice.Entities.Customer;
import com.carrentalservice.carrentalservice.Entities.Reservation;
import com.carrentalservice.carrentalservice.Repositories.BranchRepository;
import com.carrentalservice.carrentalservice.Repositories.CarRepository;
import com.carrentalservice.carrentalservice.Repositories.CustomerRepository;
import com.carrentalservice.carrentalservice.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Reservation createReservation(Reservation reservation) {

        long days = (reservation.getDateTo().getTime() - reservation.getDateFrom().getTime()) / (1000 * 60 * 60 * 24);
        double amount = reservation.getCar().getAmountPerDay() * days;
        reservation.setAmount(amount);
        reservation.setBookingDate(new Date());
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByCustomer(Customer customer) {
        return reservationRepository.findByClient(customer);
    }

    public List<Reservation> getReservationsByCar(Car car) {
        return reservationRepository.findByCar(car);
    }
}

