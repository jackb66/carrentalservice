package com.carrentalservice.carrentalservice.Services;

import com.carrentalservice.carrentalservice.Entities.Employee;
import com.carrentalservice.carrentalservice.Entities.Refund;
import com.carrentalservice.carrentalservice.Entities.Reservation;
import com.carrentalservice.carrentalservice.Repositories.EmployeeRepository;
import com.carrentalservice.carrentalservice.Repositories.RefundRepository;
import com.carrentalservice.carrentalservice.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RefundService {

    @Autowired
    private RefundRepository refundRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    public Refund createRefund(Long employeeId, Long reservationId, double surcharge, String comments) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Refund refund = new Refund();
        refund.setEmployee(employee);
        refund.setReservation(reservation);
        refund.setDateOfReturn(new Date());  // Set the current date as the return date
        refund.setSurcharge(surcharge);
        refund.setComments(comments);
        return refundRepository.save(refund);
    }

    public List<Refund> getRefundsByEmployee(Employee employee) {
        return refundRepository.findByEmployee(employee);
    }

    public List<Refund> getRefundsByReservation(Reservation reservation) {
        return refundRepository.findByReservation(reservation);
    }
}

