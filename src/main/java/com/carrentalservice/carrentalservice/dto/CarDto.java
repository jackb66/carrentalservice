package com.carrentalservice.carrentalservice.dto;

import com.carrentalservice.carrentalservice.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private String bodyType;
    private Integer year;
    private String color;
    private Double mileage;
    private Double amountPerDay;
    private String status;
    private Long branchId;
    private Double rentalAmountPerDay;

    public static CarDto toDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .bodyType(car.getBodyType())
                .year(car.getYear())
                .color(car.getColor())
                .mileage(car.getMileage())
                .amountPerDay(car.getAmountPerDay())
                .status(car.getStatus().name())
                .rentalAmountPerDay(car.getRentalAmountPerDay())
                .build();

    }
}
