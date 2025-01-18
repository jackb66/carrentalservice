package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.repositories.CarRepository;
import com.carrentalservice.carrentalservice.static_data.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    // Method to update an existing car
    public Car updateCar(Long id, Car updatedCar) {
        Optional<Car> existingCar = carRepository.findById(id);

        if (existingCar.isPresent()) {
            Car car = existingCar.get();
            car.setBrand(updatedCar.getBrand());
            car.setModel(updatedCar.getModel());
            car.setYear(updatedCar.getYear());
            car.setColor(updatedCar.getColor());
            car.setMileage(updatedCar.getMileage());
            car.setStatus(updatedCar.getStatus());
            // Save the updated car
            return carRepository.save(car);
        } else {
            throw new RuntimeException("Car not found with id " + id);
        }
    }

    // Method to get all cars
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Method to get a car by ID
    public Car getCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            return car.get();
        } else {
            throw new RuntimeException("Car not found with id " + id);
        }
    }

    // Method to delete a car by ID
    public void deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new RuntimeException("Car not found with id " + id);
        }
    }

    // Method to update car status
    public Car updateCarStatus(Long id, String status) {
        Optional<Car> existingCar = carRepository.findById(id);

        if (existingCar.isPresent()) {
            Car car = existingCar.get();
            car.setStatus(CarStatus.AVAILABLE);  // Update status (e.g., "AVAILABLE", "BOOKED", "UNAVAILABLE")
            return carRepository.save(car);
        } else {
            throw new RuntimeException("Car not found with id " + id);
        }
    }

public Car updateMileage(Long carId, int newMileage) {
    Car car = carRepository.findById(carId)
            .orElseThrow(() -> new RuntimeException("Car not found"));

    car.setMileage(newMileage);
    return carRepository.save(car);
}

public Car updateRentalAmount(Long carId, double newAmount) {
    Car car = carRepository.findById(carId)
            .orElseThrow(() -> new RuntimeException("Car not found"));

    car.setAmountPerDay(newAmount);
    return carRepository.save(car);
}

public Car changeStatus(Long carId, CarStatus status) {
    Car car = carRepository.findById(carId)
            .orElseThrow(() -> new RuntimeException("Car not found"));

    car.setStatus(status);
    return carRepository.save(car);
}

public List<Car> filterCars(String make, String model, Integer year, String color) {
    if (make != null && model != null && year != null && color != null) {
        return carRepository.findByMakeAndModelAndYear(make, model, year);
    } else if (make != null && model != null) {
        return carRepository.findByMakeAndModelAndYear(make, model, year);
    }
    return carRepository.findAll(); // If no filters are provided, return all cars
}

public List<Car> getAvailableCarsAtBranch(Long branchId) {
    return carRepository.findByBranchIdAndStatus(branchId, CarStatus.AVAILABLE.name());
}

public CarStatus getCarStatusOnDate(Long carId, LocalDate date) {
    return carRepository.findById(carId).map(Car::getStatus).orElseThrow(() -> new RuntimeException("Car not found"));
}
}