package com.carrentalservice.carrentalservice.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rental")
@Data
public class RentalCMP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String domain;
    private String contactAddress;
    private String owner;
}

