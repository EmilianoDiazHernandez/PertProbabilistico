package com.escom.pertpb;

import java.util.Map;

public class Project {

    private final Map<String, Activity> activities;

    public Project(Map<String, Activity> activities) {
        this.activities = activities;
    }

    public Map<String, Activity> getActivities() {
        return activities;
    }

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
