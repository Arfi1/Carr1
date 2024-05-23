package com.example.carrr.model;

public enum LeaseType {
    UNLIMITED(90, Integer.MAX_VALUE),
    LIMITED(120, 150);

    private final int minDays;
    private final int maxDays;

    LeaseType(int minDays, int maxDays) {
        this.minDays = minDays;
        this.maxDays = maxDays;
    }

    public int getMinDays() {
        return minDays;
    }

    public int getMaxDays() {
        return maxDays;
    }
}
