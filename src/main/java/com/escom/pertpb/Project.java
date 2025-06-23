package com.escom.pertpb;

import java.util.Map;

public record Project(Map<String, Activity> activities) {

    public double getTotalExpectedTime() {
        double total = 0.0;
        for (Activity activity : activities.values())
            total += activity.getExpectedTime();
        return total;
    }

    public double getTotalVariance() {
        double total = 0.0;
        for (Activity activity : activities.values())
            total += activity.getVariance();
        return total;
    }

    public double getTotalDeviation() {
        return Math.sqrt(getTotalVariance());
    }
}
