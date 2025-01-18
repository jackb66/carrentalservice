package com.carrentalservice.carrentalservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "revenue")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private double totalAmount;
    private BigDecimal approvedAmount = BigDecimal.ZERO;
    private BigDecimal unapprovedAmount = BigDecimal.ZERO;


    public Revenue(Reservation reservation, BigDecimal amount) {
    }
}


