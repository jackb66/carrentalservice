package com.carrentalservice.carrentalservice.entities;

import com.carrentalservice.carrentalservice.static_data.CarStatus;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Table(name = "car")
@Entity

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
    private CarStatus status;
    @ManyToOne
    private Branch branch;

}

