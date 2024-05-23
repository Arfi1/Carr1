package com.example.carrr.model;

public class Vehicle {
    private int vehicleNumber;
    private String frameNumber;
    private String brand;
    private String model;
    private int make;
    private String color;
    private double price;
    private int flow;
    private int odometer;
    private String fuelType;
    private String motor;
    private String gearType;
    private LeaseStatus isReadyForLease;

    public Vehicle() {
    }

    public Vehicle(int vehicleNumber, String frameNumber, String brand, String model, int make, String color, double price, int flow, int odometer, String fuelType, String motor, String gearType, LeaseStatus isReadyForLease) {
        this.vehicleNumber = vehicleNumber;
        this.frameNumber = frameNumber;
        this.brand = brand;
        this.model = model;
        this.make = make;
        this.color = color;
        this.price = price;
        this.flow = flow;
        this.odometer = odometer;
        this.fuelType = fuelType;
        this.motor = motor;
        this.gearType = gearType;
        this.isReadyForLease = isReadyForLease;
    }

    // Getters and Setters

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMake() {
        return make;
    }

    public void setMake(int make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public LeaseStatus getIsReadyForLease() {
        return isReadyForLease;
    }

    public void setIsReadyForLease(LeaseStatus isReadyForLease) {
        this.isReadyForLease = isReadyForLease;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNumber=" + vehicleNumber +
                ", frameNumber='" + frameNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", make=" + make +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", flow=" + flow +
                ", odometer=" + odometer +
                ", fuelType='" + fuelType + '\'' +
                ", motor='" + motor + '\'' +
                ", gearType='" + gearType + '\'' +
                ", isReadyForLease=" + isReadyForLease +
                '}';
    }
}
