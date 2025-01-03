package com.carrentalservice.carrentalservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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



}
