package com.carrentalservice.carrentalservice.Controllers;

import com.carrentalservice.carrentalservice.Entities.Revenue;
import com.carrentalservice.carrentalservice.Services.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;


    @PostMapping("/calculate")
    public Revenue calculateRevenue(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        return revenueService.calculateRevenue(startDate, endDate);
    }
}
