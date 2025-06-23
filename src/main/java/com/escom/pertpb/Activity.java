package com.escom.pertpb;

public record Activity(double optimisticTime, double mostLikelyTime, double pessimisticTime) {

    public double getExpectedTime() {
        return (optimisticTime + 4 * mostLikelyTime + pessimisticTime) / 6;
    }

    public double getVariance() {
        return Math.pow((pessimisticTime - optimisticTime) / 6, 2);
    }
}

