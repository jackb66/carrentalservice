package com.carrentalservice.carrentalservice.Controllers;

import com.carrentalservice.carrentalservice.Entities.Car;
import com.carrentalservice.carrentalservice.Services.CarService;
import com.carrentalservice.carrentalservice.Static_Data.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/available")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }

    @GetMapping("/by_id")
    public Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PutMapping("/status")
    public Car updateCarStatus(@PathVariable Long id, @RequestBody CarStatus status) {
        return carService.updateCarStatus(id, status);
    }
}

