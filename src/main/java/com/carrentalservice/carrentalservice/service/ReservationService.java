package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.dto.ReservationRequest;
import com.carrentalservice.carrentalservice.entities.Branch;
import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.entities.Customer;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.repositories.BranchRepository;
import com.carrentalservice.carrentalservice.repositories.CarRepository;
import com.carrentalservice.carrentalservice.repositories.CustomerRepository;
import com.carrentalservice.carrentalservice.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationService {

    private static final double REIMBURSEMENT_PERCENTAGE = 0.8;
    private static final int NO_FEE_DAYS = 2;

    @Value("${rental.return.surcharge}")
    private double returnSurcharge;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BranchRepository branchRepository;

    public Reservation createReservation(ReservationRequest request) {

        Customer customer = customerRepository.findByEmail(request.getCustomerEmail())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));

        if (!isCarAvailable(car.getId(), request.getDateFrom(), request.getDateTo())) {
            throw new IllegalArgumentException("Car is already reserved during the selected dates");
        }

        Branch branchOfLoan = branchRepository.findById(request.getBranchOfLoanId())
                .orElseThrow(() -> new IllegalArgumentException("Pickup branch not found"));

        Branch returnBranch = branchRepository.findById(request.getReturnBranchId())
                .orElseThrow(() -> new IllegalArgumentException("Return branch not found"));

        long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(request.getDateFrom(), request.getDateTo());
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Invalid rental period");
        }

        double baseCost = rentalDays * car.getAmountPerDay();
        double surcharge = branchOfLoan.equals(returnBranch) ? 0 : returnSurcharge;
        double totalAmount = baseCost + surcharge;

        Reservation reservation = Reservation.builder()
                .customer(customer)
                .car(car)
                .dateFrom(request.getDateFrom())
                .dateTo(request.getDateTo())
                .branchOfLoan(branchOfLoan)
                .returnBranch(returnBranch)
                .amount(totalAmount)
                .bookingDate(LocalDate.now().atStartOfDay())
                .build();

        return reservationRepository.save(reservation);
    }

    public boolean isCarAvailable(Long carId, LocalDate dateFrom, LocalDate dateTo) {
        List<Reservation> overlappingReservations = reservationRepository
                .findByCarIdAndDateFromLessThanEqualAndDateToGreaterThanEqual
                        (carId, dateTo, dateFrom);
        return overlappingReservations.isEmpty();
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    public Reservation cancelReservation(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if (reservation.isCanceled()) {
            throw new IllegalStateException("Reservation is already canceled");
        }

        long daysBeforeRental = ChronoUnit.DAYS.between(LocalDate.now(), reservation.getDateFrom());

        double refundAmount;
        if (daysBeforeRental >= NO_FEE_DAYS) {

            refundAmount = 0;
        } else {

            refundAmount = reservation.getAmount() * REIMBURSEMENT_PERCENTAGE;
        }

        reservation.setCanceled(true);
        reservation.setSetRefundAmount(refundAmount);

        return reservationRepository.save(reservation);
    }
}


