package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Branch;
import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.entities.Customer;
import com.carrentalservice.carrentalservice.entities.Reservation;
import com.carrentalservice.carrentalservice.repositories.BranchRepository;
import com.carrentalservice.carrentalservice.repositories.CarRepository;
import com.carrentalservice.carrentalservice.repositories.CustomerRepository;
import com.carrentalservice.carrentalservice.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

    @Service
    public class ReservationService {

        private final ReservationRepository reservationRepository;
        private final CarRepository carRepository;
        private final BranchRepository branchRepository;
        private final CustomerRepository customerRepository;

        @Autowired
        public ReservationService(ReservationRepository reservationRepository,
                                  CarRepository carRepository,
                                  BranchRepository branchRepository,
                                  CustomerRepository customerRepository) {
            this.reservationRepository = reservationRepository;
            this.carRepository = carRepository;
            this.branchRepository = branchRepository;
            this.customerRepository = customerRepository;
        }

        // Method to create a reservation
        public Reservation createReservation(Long customerId, Long carId, Long pickupBranchId, Long returnBranchId,
                                             LocalDate startDate, LocalDate endDate) {

            // Fetch entities
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
            Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
            Branch pickupBranch = branchRepository.findById(pickupBranchId).orElseThrow(() -> new RuntimeException("Pickup branch not found"));
            Branch returnBranch = branchRepository.findById(returnBranchId).orElseThrow(() -> new RuntimeException("Return branch not found"));

            // Calculate total rental amount
            double totalAmount = calculateTotalAmount(car, startDate, endDate, pickupBranch, returnBranch);

            // Create reservation
            Reservation reservation = new Reservation();
            reservation.setCustomer(customer);
            reservation.setCar(car);
            reservation.setPickupBranch(pickupBranch);
            reservation.setReturnBranch(returnBranch);
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            reservation.setTotalAmount(totalAmount);
            reservation.setCancelled(false);

            return reservationRepository.save(reservation);
        }

        // Calculate the total amount based on days and location return fee
        private double calculateTotalAmount(Car car, LocalDate startDate, LocalDate endDate, Branch pickupBranch, Branch returnBranch) {
            long days = ChronoUnit.DAYS.between(startDate, endDate);
            double rentalAmount = car.getRentalAmountPerDay() * days;
            double locationFee = 0;

            // If return branch is different from pickup branch, add a location fee
            if (!pickupBranch.equals(returnBranch)) {
                locationFee = 50; // Fixed fee for returning at a different location (adjustable)
            }

            return rentalAmount + locationFee;
        }

        // Method to cancel reservation
        public String cancelReservation(Long reservationId) {
            Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found"));

            if (reservation.isCancelled()) {
                return "This reservation is already cancelled.";
            }

            LocalDate today = LocalDate.now();
            long daysBeforeRental = ChronoUnit.DAYS.between(today, reservation.getStartDate());

            if (daysBeforeRental >= 2) {
                reservation.setCancelled(true);
                reservation.setCancellationFee(0); // Full refund
            } else {
                reservation.setCancelled(true);
                reservation.setCancellationFee(reservation.getTotalAmount() * 0.2); // 80% refund, 20% cancellation fee
            }

            reservationRepository.save(reservation);

            return reservation.isCancelled() ? "Reservation cancelled successfully." : "Cancellation failed.";
        }
    }
