package com.carrentalservice.carrentalservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Table(name = "revenue")
@Entity

public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private double totalAmount;

}

