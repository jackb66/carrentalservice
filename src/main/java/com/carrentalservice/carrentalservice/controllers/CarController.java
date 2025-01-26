package com.carrentalservice.carrentalservice.controllers;

import com.carrentalservice.carrentalservice.dto.CarDto;
import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCar(@RequestBody CarDto carDto) {
        carService.createCar(carDto);
        return ResponseEntity.ok("Car created successfully!");
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