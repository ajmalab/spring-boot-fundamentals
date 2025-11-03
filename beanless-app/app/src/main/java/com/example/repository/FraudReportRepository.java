package com.example.repository;

import com.example.database.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FraudReportRepository {
    private final DatabaseConnector connector;

    public FraudReportRepository(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void reportFraudulentActivity(String userId, String activityType, String description) throws SQLException {
        String query = "INSERT INTO fraud_reports (user_id, activity_type, description, status) VALUES (?, ?, ?, 'PENDING')";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, activityType);
            statement.setString(3, description);
            statement.executeUpdate();
            System.out.println("Fraud report created for user: " + userId);
        }
    }

    public ResultSet getFraudReport(String reportId) throws SQLException {
        String query = "SELECT * FROM fraud_reports WHERE report_id = ?";
        PreparedStatement statement = connector.getConnection().prepareStatement(query);
        statement.setString(1, reportId);
        return statement.executeQuery();
    }

    public ResultSet getAllPendingFraudReports() throws SQLException {
        String query = "SELECT * FROM fraud_reports WHERE status = 'PENDING' ORDER BY created_at DESC";
        PreparedStatement statement = connector.getConnection().prepareStatement(query);
        return statement.executeQuery();
    }

    public void updateFraudReportStatus(String reportId, String status) throws SQLException {
        String query = "UPDATE fraud_reports SET status = ? WHERE report_id = ?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(query)) {
            statement.setString(1, status);
            statement.setString(2, reportId);
            statement.executeUpdate();
            System.out.println("Fraud report status updated: " + reportId + " -> " + status);
        }
    }

    public void flagUserAccount(String userId, String reason) throws SQLException {
        String query = "UPDATE users SET flagged = true, flag_reason = ? WHERE user_id = ?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(query)) {
            statement.setString(1, reason);
            statement.setString(2, userId);
            statement.executeUpdate();
            System.out.println("User account flagged: " + userId);
        }
    }

    public ResultSet getFlaggedUsers() throws SQLException {
        String query = "SELECT * FROM users WHERE flagged = true";
        PreparedStatement statement = connector.getConnection().prepareStatement(query);
        return statement.executeQuery();
    }

    public ResultSet getFraudStatistics() throws SQLException {
        String query = "SELECT activity_type, COUNT(*) as count, status FROM fraud_reports GROUP BY activity_type, status";
        PreparedStatement statement = connector.getConnection().prepareStatement(query);
        return statement.executeQuery();
    }
}
