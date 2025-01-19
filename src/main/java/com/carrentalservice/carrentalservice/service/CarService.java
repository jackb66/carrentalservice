package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.dto.CarDto;
import com.carrentalservice.carrentalservice.entities.Branch;
import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.repositories.BranchRepository;
import com.carrentalservice.carrentalservice.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarService {

    private final CarRepository carRepository;
    private final BranchRepository branchRepository;

    public CarService(CarRepository carRepository, BranchRepository branchRepository) {
        this.carRepository = carRepository;
        this.branchRepository = branchRepository;
    }

    public Car updateCarMileageAndAmount(CarDto carDetails) {
        Car car = carRepository.findById(carDetails.getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (carDetails.getId() != null)
            car.setId(carDetails.getId());

        if (carDetails.getBrand() != null)
            car.setBrand(carDetails.getBrand());

        if (carDetails.getModel() != null)
            car.setModel(carDetails.getModel());

        if (carDetails.getBodyType() != null)
            car.setBodyType(carDetails.getBodyType());

        if (carDetails.getYear() != null)
            car.setYear(carDetails.getYear());

        if (carDetails.getColor() != null)
            car.setYear(carDetails.getYear());

        if (carDetails.getMileage() != null)
            car.setMileage(carDetails.getMileage());

        if (carDetails.getAmountPerDay() != null)
            car.setAmountPerDay(carDetails.getAmountPerDay());

        if (carDetails.getStatus() != null)
            carDetails.setStatus(carDetails.getStatus());

        if (carDetails.getBranchId() != null)
            carDetails.setBranchId(carDetails.getBranchId());

        if (carDetails.getRentalAmountPerDay() != null)
            car.setRentalAmountPerDay(carDetails.getRentalAmountPerDay());

        if (carDetails.getBranchId() != null) {
            Branch branch = branchRepository.findById(carDetails.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found"));
            car.setBranch(branch);
        }
        return carRepository.save(car);
    }

    public List<CarDto> findAll() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(CarDto::toDto)
                .toList();
    }

    private Car findById(Long id){
        return carRepository.findById(id)
                .orElseThrow();
    }

}


