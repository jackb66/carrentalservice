package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.dto.CarDto;
import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/filter")
    public List<CarDto> filterCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String color
    ) {
        return carService.filterCars(brand, model, year, color);
    }
}