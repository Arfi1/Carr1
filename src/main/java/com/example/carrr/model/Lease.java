package com.example.carrr.model;

import java.time.LocalDate;

public class Lease {
    private int contractId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private Vehicle vehicle;
    private Employee employee;
    private Customer customer;
    private LeaseStatus isPaid;
    private LeaseType leaseType;
    private Pickup pickup;

    public Lease() {}

    public Lease(int contractId, LocalDate startDate, LocalDate endDate, double price, Vehicle vehicle, Employee employee, Customer customer, LeaseStatus isPaid, Pickup pickup, LeaseType leaseType) {
        this.contractId = contractId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.vehicle = vehicle;
        this.employee = employee;
        this.customer = customer;
        this.isPaid = isPaid;
        this.pickup = pickup;
        this.leaseType = leaseType;
    }

    // Getters and Setters
    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LeaseStatus getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(LeaseStatus isPaid) {
        this.isPaid = isPaid;
    }

    public LeaseType getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(LeaseType leaseType) {
        this.leaseType = leaseType;
    }

    public Pickup getPickup() {
        return pickup;
    }

    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
    }

    @Override
    public String toString() {
        return "Lease{" +
                "contractId=" + contractId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", vehicle=" + vehicle +
                ", employee=" + employee +
                ", customer=" + customer +
                ", isPaid=" + isPaid +
                ", leaseType=" + leaseType +
                ", pickup=" + pickup +
                '}';
    }
}
