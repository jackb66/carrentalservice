package com.carrentalservice.carrentalservice.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@Table(name = "reservation")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime bookingDate;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Car car;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    @OneToOne
    private Branch branchOfLoan;
    @OneToOne
    private Branch returnBranch;
    private Double amount;
    @OneToOne(mappedBy = "reservation")
    private Refund refund;
    @OneToOne(mappedBy = "reservation")
    private Loan loan;

    public void setCanceled(boolean b) {
    }

    public void setRefundAmount(double refundAmount) {
    }

    public boolean isCanceled() {
        return false;
    }
}
