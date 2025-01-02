package com.carrentalservice.carrentalservice.Controllers;

import com.carrentalservice.carrentalservice.Entities.Branch;
import com.carrentalservice.carrentalservice.Entities.Rental;
import com.carrentalservice.carrentalservice.Services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.createRental(rental);
    }

    @GetMapping("/by_id")
    public Rental getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @GetMapping("all_rentals")
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }
}


