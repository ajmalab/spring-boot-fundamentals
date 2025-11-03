package com.example.service;

import com.example.repository.FraudReportRepository;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FraudService {
    private FraudReportRepository fraudReportRepository;

    public FraudService(FraudReportRepository fraudReportRepository) {
        this.fraudReportRepository = fraudReportRepository;
    }

    public void reportFraudulentActivity(String userId, String activityType, String description) {
        try {
            fraudReportRepository.reportFraudulentActivity(userId, activityType, description);
            System.out.println("FraudService: Fraudulent activity reported successfully");
        } catch (SQLException e) {
            System.err.println("FraudService: Error reporting fraudulent activity - " + e.getMessage());
        }
    }

    public void getFraudReport(String reportId) {
        try {
            ResultSet rs = fraudReportRepository.getFraudReport(reportId);
            if (rs.next()) {
                System.out.println("FraudService: Fraud Report - ID: " + rs.getString("report_id") +
                                 ", User: " + rs.getString("user_id") +
                                 ", Type: " + rs.getString("activity_type") +
                                 ", Status: " + rs.getString("status"));
            } else {
                System.out.println("FraudService: Report not found");
            }
        } catch (SQLException e) {
            System.err.println("FraudService: Error retrieving fraud report - " + e.getMessage());
        }
    }

    public void reviewPendingReports() {
        try {
            ResultSet rs = fraudReportRepository.getAllPendingFraudReports();
            System.out.println("FraudService: Reviewing pending fraud reports...");
            while (rs.next()) {
                System.out.println("Report ID: " + rs.getString("report_id") +
                                 ", User: " + rs.getString("user_id") +
                                 ", Type: " + rs.getString("activity_type") +
                                 ", Description: " + rs.getString("description"));
            }
        } catch (SQLException e) {
            System.err.println("FraudService: Error reviewing pending reports - " + e.getMessage());
        }
    }

    public void approveFraudReport(String reportId) {
        try {
            fraudReportRepository.updateFraudReportStatus(reportId, "APPROVED");
            System.out.println("FraudService: Fraud report approved");
        } catch (SQLException e) {
            System.err.println("FraudService: Error approving fraud report - " + e.getMessage());
        }
    }

    public void rejectFraudReport(String reportId) {
        try {
            fraudReportRepository.updateFraudReportStatus(reportId, "REJECTED");
            System.out.println("FraudService: Fraud report rejected");
        } catch (SQLException e) {
            System.err.println("FraudService: Error rejecting fraud report - " + e.getMessage());
        }
    }

    public void flagUserAccount(String userId, String reason) {
        try {
            fraudReportRepository.flagUserAccount(userId, reason);
            System.out.println("FraudService: User account flagged for suspicious activity");
        } catch (SQLException e) {
            System.err.println("FraudService: Error flagging user account - " + e.getMessage());
        }
    }

    public void viewFlaggedUsers() {
        try {
            ResultSet rs = fraudReportRepository.getFlaggedUsers();
            System.out.println("FraudService: Retrieving flagged user accounts...");
            while (rs.next()) {
                System.out.println("User ID: " + rs.getString("user_id") +
                                 ", Reason: " + rs.getString("flag_reason"));
            }
        } catch (SQLException e) {
            System.err.println("FraudService: Error retrieving flagged users - " + e.getMessage());
        }
    }

    public void generateFraudStatistics() {
        try {
            ResultSet rs = fraudReportRepository.getFraudStatistics();
            System.out.println("FraudService: Fraud Statistics Report");
            System.out.println("==========================================");
            while (rs.next()) {
                System.out.println("Activity Type: " + rs.getString("activity_type") +
                                 ", Count: " + rs.getInt("count") +
                                 ", Status: " + rs.getString("status"));
            }
        } catch (SQLException e) {
            System.err.println("FraudService: Error generating fraud statistics - " + e.getMessage());
        }
    }
}
