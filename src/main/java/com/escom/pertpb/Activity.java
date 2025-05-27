package com.escom.pertpb;

public class Activity {

    private double optimisticTime;
    private double mostLikelyTime;
    private double pessimisticTime;
    private double expectedTime;
    private double variance;

    public Activity(double optimisticTime, double mostLikelyTime, double pessimisticTime) {
        this.optimisticTime = optimisticTime;
        this.mostLikelyTime = mostLikelyTime;
        this.pessimisticTime = pessimisticTime;
        this.expectedTime = (optimisticTime + 4 * mostLikelyTime + pessimisticTime) / 6;
        this.variance = Math.pow((pessimisticTime - optimisticTime) / 6, 2);
    }

    public double getExpectedTime() {
        return expectedTime;
    }

    public double getVariance() {
        return variance;
    }
    public double getOptimisticTime() {
        return optimisticTime;
    }

    public double getMostLikelyTime() {
        return mostLikelyTime;
    }

    public double getPessimisticTime() {
        return pessimisticTime;
    }
}
