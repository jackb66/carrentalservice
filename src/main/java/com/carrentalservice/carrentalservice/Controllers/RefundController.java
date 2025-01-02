package com.carrentalservice.carrentalservice.Controllers;

import com.carrentalservice.carrentalservice.Entities.Employee;
import com.carrentalservice.carrentalservice.Entities.Refund;
import com.carrentalservice.carrentalservice.Entities.Reservation;
import com.carrentalservice.carrentalservice.Repositories.EmployeeRepository;
import com.carrentalservice.carrentalservice.Repositories.RefundRepository;
import com.carrentalservice.carrentalservice.Repositories.ReservationRepository;
import com.carrentalservice.carrentalservice.Services.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {

    @Autowired
    private RefundService refundService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RefundRepository refundRepository;


    @PostMapping
    public Refund createRefund(@RequestParam Long employeeId,
                               @RequestParam Long reservationId,
                               @RequestParam double surcharge,
                               @RequestParam String comments) {
        return refundService.createRefund(employeeId, reservationId, surcharge, comments);
    }

    @GetMapping("by_employee_Id")
    public List<Refund> getRefundsByEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return refundService.getRefundsByEmployee(employee);
    }

    @GetMapping("/by_reservation_Id")
    public List<Refund> getRefundsByReservation(@PathVariable Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        return refundService.getRefundsByReservation(reservation);
    }
}
