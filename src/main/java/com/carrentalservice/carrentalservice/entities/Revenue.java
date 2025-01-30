package com.carrentalservice.carrentalservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//fitimi per vitin aktual per nj branch te caktuar
//fitimin per nje branch te caktuar duke filluar nga nje date e caktuar
//fitimin per nje rental nga nje date deri ne nje date
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Revenue {

    private LocalDate startDate;
    private Double totalAmount;
    private LocalDate endDate;


}

