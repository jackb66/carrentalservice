package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.*;
import com.carrentalservice.carrentalservice.repositories.*;
import com.carrentalservice.carrentalservice.static_data.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class ReturnService {
    @Autowired
    private RefundRepository refundRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Refund returnCar(Long reservationId, Long employeeId, double surcharge, String comments) {
        // Fetch the reservation and employee
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Update car status to AVAILABLE
        Car car = reservation.getCar();
        car.setStatus(CarStatus.AVAILABLE);
        carRepository.save(car);

        // Create a refund (return event)
        Refund refund = new Refund();
        refund.setReservation(reservation);
        refund.setEmployee(employee);
        refund.setDateOfReturn(LocalDate.now());
        refund.setSurcharge(surcharge);
        refund.setComments(comments);

        // Save the refund
        refund = refundRepository.save(refund);

        // Optionally, generate an invoice for the return
        Invoice invoice = new Invoice();
        invoice.setReservation(reservation);
        invoice.setAmount(reservation.getTotalAmount() + surcharge); // Rental amount + surcharge
        invoice.setPaid(false); // Mark the invoice as unpaid initially
        invoiceRepository.save(invoice);

        // Return the refund details
        return refund;
    }
}
