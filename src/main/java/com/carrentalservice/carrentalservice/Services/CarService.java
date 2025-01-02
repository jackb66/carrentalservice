package com.carrentalservice.carrentalservice.Services;

import com.carrentalservice.carrentalservice.Entities.Car;
import com.carrentalservice.carrentalservice.Repositories.CarRepository;
import com.carrentalservice.carrentalservice.Static_Data.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAvailableCars() {
        return carRepository.findByStatus(CarStatus.AVAILABLE);
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public Car updateCarStatus(Long id, CarStatus status) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        car.setStatus(status);
        car.setStatusDate(new Date());
        return carRepository.save(car);
    }
}
