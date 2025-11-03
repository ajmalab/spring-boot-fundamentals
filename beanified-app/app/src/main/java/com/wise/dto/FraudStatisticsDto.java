package com.wise.dto;

public class FraudStatisticsDto {
    private String activityType;
    private int count;
    private String status;

    public FraudStatisticsDto(String activityType, int count, String status) {
        this.activityType = activityType;
        this.count = count;
        this.status = status;
    }

    // Getters and setters
    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
