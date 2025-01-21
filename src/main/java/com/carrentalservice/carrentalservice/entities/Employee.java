package com.carrentalservice.carrentalservice.entities;


import com.carrentalservice.carrentalservice.static_data.Position;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
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
    private String firstname;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Position position;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @Column(unique = true)
    private String username;
    private String password;
    private Boolean isActive;

}
