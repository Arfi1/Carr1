package com.example.carrr.model;

import java.time.LocalDate;

public class Pickup {
    private int pickupId;
    private Lease lease;
    private LocalDate pickupDate;
    private String location;
    private DeliveredStatus delivered;

    public Pickup() {
    }

    public Pickup(int pickupId, Lease lease, LocalDate pickupDate, String location, DeliveredStatus delivered) {
        this.pickupId = pickupId;
        this.lease = lease;
        this.pickupDate = pickupDate;
        this.location = location;
        this.delivered = delivered;
    }

    // Getters and Setters

    public int getPickupId() {
        return pickupId;
    }

    public void setPickupId(int pickupId) {
        this.pickupId = pickupId;
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DeliveredStatus getDelivered() {
        return delivered;
    }

    public void setDelivered(DeliveredStatus delivered) {
        this.delivered = delivered;
    }

    @Override
    public String toString() {
        return "Pickup{" +
                "pickupId=" + pickupId +
                ", lease=" + lease +
                ", pickupDate=" + pickupDate +
                ", location='" + location + '\'' +
                ", delivered=" + delivered +
                '}';
    }
}
