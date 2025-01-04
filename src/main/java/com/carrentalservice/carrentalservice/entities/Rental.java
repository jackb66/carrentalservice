package  com.carrentalservice.carrentalservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "rental")
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String internetDomain;
    private String contactAddress;
    private String owner;
    private String logotype;
    @OneToMany(mappedBy = "rental")
    private List<Branch> branches;

}
