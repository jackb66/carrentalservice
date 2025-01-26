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
    private EmployeeRepository employeeRepository;

    public Refund returnCar(Long reservationId, Long employeeId, double surcharge, String comments) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));


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
