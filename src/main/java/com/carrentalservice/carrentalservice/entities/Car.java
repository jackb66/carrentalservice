package com.carrentalservice.carrentalservice.entities;

import com.carrentalservice.carrentalservice.static_data.CarStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Table(name = "car") //specifies it
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //primary key
    private Long id;
    private String brand;
    private String model;
    private String bodyType;
    private Integer year;
    private String color;
    private Double mileage;
    private Double amountPerDay;
    @Enumerated(EnumType.STRING)//stores statusin e makines si nje string in database
    private CarStatus status;
    @ManyToOne //vendos nje relationship many to one me entitetin branch ;)
    private Branch branch;
}


