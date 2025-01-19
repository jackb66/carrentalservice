package com.carrentalservice.carrentalservice.entities;

import com.carrentalservice.carrentalservice.static_data.CarStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Table(name = "car")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String bodyType;
    private Integer year;
    private String color;
    private Double mileage;
    private Double amountPerDay;
    @Enumerated(EnumType.STRING)
    private CarStatus Status;
    @ManyToOne
    private Branch branch;
    private double rentalAmountPerDay;
}

