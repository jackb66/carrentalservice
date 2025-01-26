package com.carrentalservice.carrentalservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@Table(name = "customer")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String address;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Reservation> reservations;
    @ManyToOne
    private Rental rental;

}


