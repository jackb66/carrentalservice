package com.carrentalservice.carrentalservice.Entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingDate;  // Date when the reservation was made

    @ManyToOne
    private Customer client;  // The customer making the reservation

    @ManyToOne
    private Car car;  // The car being reserved

    @Temporal(TemporalType.DATE)
    private Date dateFrom;  // Start date of the reservation

    @Temporal(TemporalType.DATE)
    private Date dateTo;  // End date of the reservation

    @ManyToOne
    private Branch branchOfLoan;  // Branch where the car is being rented from

    @ManyToOne
    private Branch returnBranch;  // Branch where the car will be returned

    private double amount;  // Total amount for the reservation

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Customer getClient() {
        return client;
    }

    public void setClient(Customer client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Branch getBranchOfLoan() {
        return branchOfLoan;
    }

    public void setBranchOfLoan(Branch branchOfLoan) {
        this.branchOfLoan = branchOfLoan;
    }

    public Branch getReturnBranch() {
        return returnBranch;
    }

    public void setReturnBranch(Branch returnBranch) {
        this.returnBranch = returnBranch;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
