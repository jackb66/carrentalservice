package com.carrentalservice.carrentalservice.entities;


import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    private LocalDateTime bookingDate;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Car car;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    @OneToOne
    private Branch branchOfLoan;
    @OneToOne
    private Branch returnBranch;
    private Double amount;
    @OneToOne(mappedBy = "reservation")
    private Refund refund;
}
