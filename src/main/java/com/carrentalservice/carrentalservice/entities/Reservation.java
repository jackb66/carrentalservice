package com.carrentalservice.carrentalservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data //gjeneron getter setter hashcode to string equals
@Builder
@Table(name = "reservation")//specifies it
@Entity//marks as JPA entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key
    private Long id;
    private LocalDateTime bookingDate;
    @ManyToOne//vendos nje relationship many to one me entitetin e customerit ;)
    private Customer customer;
    @ManyToOne //vendos nje relationship many to one me entitetin e makines ;)
    private Car car;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Double setCanceled;
    private Double setRefundAmount;
    private boolean isCanceled;
    @OneToOne//one-to-one relationship with the Branch
    private Branch branchOfLoan;
    @OneToOne
    private Branch returnBranch;
    private Double amount;
    @OneToOne(mappedBy = "reservation")
    @JsonIgnore
    private Refund refund;
    @OneToOne(mappedBy = "reservation")
    @JsonIgnore
    private Loan loan;
}
