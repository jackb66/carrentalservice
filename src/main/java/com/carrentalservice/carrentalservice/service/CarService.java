package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.dto.CarDto;
import com.carrentalservice.carrentalservice.entities.Branch;
import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.repositories.BranchRepository;
import com.carrentalservice.carrentalservice.repositories.CarRepository;
import com.carrentalservice.carrentalservice.static_data.CarStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CarService {

    private final CarRepository carRepository;
    private final BranchRepository branchRepository;
    private final EntityManager entityManager;

    public CarService(CarRepository carRepository, BranchRepository branchRepository, EntityManager entityManager) {
        this.carRepository = carRepository;
        this.branchRepository = branchRepository;
        this.entityManager = entityManager;
    }

    public void createCar(CarDto carDetails) {
        Car car = new Car();
        car.setBrand(carDetails.getBrand());
        car.setModel(carDetails.getModel());
        car.setBodyType(carDetails.getBodyType());
        car.setYear(carDetails.getYear());
        car.setColor(carDetails.getColor());
        car.setMileage(carDetails.getMileage());
        car.setAmountPerDay(carDetails.getAmountPerDay());
        car.setStatus(CarStatus.valueOf(carDetails.getStatus()));
        car.setAmountPerDay(carDetails.getRentalAmountPerDay());

        if (carDetails.getBranchId() != null) {
            Branch branch = branchRepository.findById(carDetails.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found"));
            car.setBranch(branch);
        }

        carRepository.save(car);
    }

    public Car updateCarMileageAndAmount(CarDto carDetails) {
        Car car = carRepository.findById(carDetails.getId())
                .orElseThrow();

        if (carDetails.getBrand() != null)
            car.setBrand(carDetails.getBrand());

        if (carDetails.getModel() != null)
            car.setModel(carDetails.getModel());

        if (carDetails.getBodyType() != null)
            car.setBodyType(carDetails.getBodyType());

        if (carDetails.getYear() != null)
            car.setYear(carDetails.getYear());

        if (carDetails.getColor() != null)
            car.setColor(carDetails.getColor());

        if (carDetails.getMileage() != null)
            car.setMileage(carDetails.getMileage());

        if (carDetails.getAmountPerDay() != null)
            car.setAmountPerDay(carDetails.getAmountPerDay());

        if (carDetails.getStatus() != null)
            car.setStatus(CarStatus.valueOf(carDetails.getStatus()));

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

    public Car findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow();
    }

    public List<CarDto> findByBranchId(Long branchId){
        List<Car> cars = carRepository.findAllByBranch_Id(branchId);
        return cars.stream()
                .map(CarDto::toDto)
                .toList();
    }

    public List<CarDto> filterCars(String brand, String model, Integer year, String color) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> car = query.from(Car.class);

        List<Predicate> predicates = new ArrayList<>();

        if (brand != null) {
            predicates.add(cb.like(cb.lower(car.get("brand")), "%" + brand.toLowerCase() + "%"));
        }
        if (model != null) {
            predicates.add(cb.like(cb.lower(car.get("model")), "%" + model.toLowerCase() + "%"));
        }
        if (year != null) {
            predicates.add(cb.equal(car.get("year"), year));
        }
        if (color != null) {
            predicates.add(cb.like(cb.lower(car.get("color")), "%" + color.toLowerCase() + "%"));
        }
        query.select(car).where(cb.and(predicates.toArray(new Predicate[0])));
        List<Car> cars = entityManager.createQuery(query).getResultList();
        return cars.stream().map(CarDto::toDto).toList();
    }
}





