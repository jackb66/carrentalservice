package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.dto.CarDto;
import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.service.CarService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PutMapping("/update")
    public Car updateCarMileageAndAmount(@RequestBody CarDto carDetails) {
        return carService.updateCarMileageAndAmount(carDetails);
    }
}