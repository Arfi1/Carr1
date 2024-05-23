package com.example.carrr.model;

public class DamageReport {
    private int reportId;
    private double totalPrice;
    private String damageDescription;
    private Lease lease;

    public DamageReport() {
    }

    public DamageReport(int reportId, double totalPrice, String damageDescription, Lease lease) {
        this.reportId = reportId;
        this.totalPrice = totalPrice;
        this.damageDescription = damageDescription;
        this.lease = lease;
    }

    // Getters and Setters

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    @Override
    public String toString() {
        return "DamageReport{" +
                "reportId=" + reportId +
                ", totalPrice=" + totalPrice +
                ", damageDescription='" + damageDescription + '\'' +
                ", lease=" + lease +
                '}';
    }
}