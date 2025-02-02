package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.*;
import com.carrentalservice.carrentalservice.repositories.*;
import com.carrentalservice.carrentalservice.static_data.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReturnService {
    @Autowired
    private RefundRepository refundRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EmployeeService employeeService;

    public Refund returnCar(Long reservationId, double surcharge, String comments) {

        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found"));

        Employee employee = employeeService.findLoggedInUser();


        Car car = reservation.getCar();
        car.setStatus(CarStatus.AVAILABLE);
        carRepository.save(car);
        Refund refund = new Refund();
        refund.setReservation(reservation);
        refund.setEmployee(employee);
        refund.setDateOfReturn(LocalDate.now());
        refund.setSurcharge(surcharge);
        refund.setComments(comments);


        refund = refundRepository.save(refund);

        return refund;
    }
}
