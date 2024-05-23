package com.example.carrr.model;

import java.time.LocalDate;

public class Transaktion {
    private int transactionId;
    private LocalDate transactionDate;
    private double amount;
    private Lease lease;
    private DamageReport damageReport;

    public Transaktion() {
    }

    public Transaktion(int transactionId, LocalDate transactionDate, double amount, Lease lease, DamageReport damageReport) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.lease = lease;
        this.damageReport = damageReport;
    }

    // Getters and Setters

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    public DamageReport getDamageReport() {
        return damageReport;
    }

    public void setDamageReport(DamageReport damageReport) {
        this.damageReport = damageReport;
    }

    @Override
    public String toString() {
        return "Transaktion{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                ", lease=" + lease +
                ", damageReport=" + damageReport +
                '}';
    }
}