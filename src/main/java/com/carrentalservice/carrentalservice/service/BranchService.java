package com.carrentalservice.carrentalservice.service;

import com.carrentalservice.carrentalservice.entities.Branch;
import com.carrentalservice.carrentalservice.entities.Car;
import com.carrentalservice.carrentalservice.entities.Rental;
import com.carrentalservice.carrentalservice.repositories.BranchRepository;
import com.carrentalservice.carrentalservice.repositories.CarRepository;
import com.carrentalservice.carrentalservice.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private RentalRepository rentalRepository;

    //Mbaroje
    public Branch createBranch(Branch branch, Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found with id " + rentalId));
        branch.setRental(rental);
        branch.setActive(true);
        return branchRepository.save(branch);
    }

    //Mbaroje
    public Branch updateBranch(Long id, Branch updatedBranch) {
        Branch branch = getBranchById(id);
        branch.setName(updatedBranch.getName());
        branch.setAddress(updatedBranch.getAddress());
        branch.setCity(updatedBranch.getCity());
        return branchRepository.save(branch);
    }

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch getBranchById(Long id) {
        Optional<Branch> branch = branchRepository.findById(id);
        if (branch.isPresent()) {
            return branch.get();
        } else {
            throw new RuntimeException("Branch not found with id " + id);
        }
    }
    //Krijo nje metode qe mbyll nje branch
    public Branch closeBranch(Long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found with id " + branchId));
        branch.setActive(false);
        return branchRepository.save(branch);
    }
    //Nje metode qe jep te gjithq makinat e disponueshme te nje branchi
    // nga data qe kalon perdoruesi e ne vazhdim
    public List<Car> getAvailableCars(Long branchId, LocalDate fromDate) {
        CarRepository carRepository = null;
        List<Car> allBranchCars = carRepository.findByBranchId(branchId);
        Object reservationRepository = null;
        List<Long> reservedCarIds = List.of();
        reservationRepository.notifyAll();
        return allBranchCars.stream()
                .filter(car -> !reservedCarIds.contains(car.getId()))
                .collect(Collectors.toList());
    }

}




