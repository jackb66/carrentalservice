package com.carrentalservice.carrentalservice.entities;

import com.carrentalservice.carrentalservice.entities.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table(name = "loan")
@Entity
@AllArgsConstructor
@NoArgsConstructor
    public class Invoice {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private Reservation reservation;

        private double amount;

        private boolean paid;
}
