package com.wise.dto;

import java.sql.Timestamp;

public class FraudReportDto {
    private Long reportId;
    private String userId;
    private String activityType;
    private String description;
    private String status;
    private Timestamp createdAt;

    public FraudReportDto(Long reportId, String userId, String activityType, String description, String status, Timestamp createdAt) {
        this.reportId = reportId;
        this.userId = userId;
        this.activityType = activityType;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
