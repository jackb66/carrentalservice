package com.carrentalservice.carrentalservice.entities;


import com.carrentalservice.carrentalservice.static_data.Position;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table(name = "employee")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String name;
    @Enumerated(EnumType.STRING)
    private Position position;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;


}
