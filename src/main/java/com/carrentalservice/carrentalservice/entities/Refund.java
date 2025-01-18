package com.carrentalservice.carrentalservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@Table(name = "refund")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private LocalDate dateOfReturn;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    private Double surcharge;
    private String comments;

}

