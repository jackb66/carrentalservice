package com.carrentalservice.carrentalservice.entities;

import com.carrentalservice.carrentalservice.static_data.CarStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@Table(name = "car")
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

