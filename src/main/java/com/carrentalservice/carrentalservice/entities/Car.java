package com.carrentalservice.carrentalservice.entities;

import com.carrentalservice.carrentalservice.static_data.CarStatus;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Data
@Table(name = "car")
@Entity

public class Car {

    @Id
    private Long id;
    private String brand;
    private String model;
    private String bodyType;
    private int year;
    private String color;
    private double mileage;
    private double amountPerDay;
    @Enumerated(EnumType.STRING)
    private CarStatus status;
    private Date statusDate;
    @ManyToOne
    private Branch branch;

}

