package com.carrentalservice.carrentalservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Table(name = "reservation")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingDate;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Car car;
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    @ManyToOne
    private Branch branchOfLoan;
    @ManyToOne
    private Branch returnBranch;
    @ManyToOne
    private Branch pickupBranch;
    private LocalDate startDate; // Rental start date
    private LocalDate endDate; // Rental end date

    private double totalAmount; // Total rental amount

    private boolean isCancelled; // Whether the reservation is cancelled

    private double cancellationFee;
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices; // A reservation can have multiple invoices
}
