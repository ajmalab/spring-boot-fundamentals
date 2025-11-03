package com.example.repository;

import com.example.database.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRepository {
    private final DatabaseConnector connector;

    public PaymentRepository(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void savePayment(String userId, double amount, String paymentMethod, String status) throws SQLException {
        String query = "INSERT INTO payments (user_id, amount, payment_method, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setDouble(2, amount);
            statement.setString(3, paymentMethod);
            statement.setString(4, status);
            statement.executeUpdate();
            System.out.println("Payment recorded: " + amount + " for user " + userId);
        }
    }

    public ResultSet getPayment(String paymentId) throws SQLException {
        String query = "SELECT * FROM payments WHERE payment_id = ?";
        PreparedStatement statement = connector.getConnection().prepareStatement(query);
        statement.setString(1, paymentId);
        return statement.executeQuery();
    }

    public ResultSet getPaymentsByUser(String userId) throws SQLException {
        String query = "SELECT * FROM payments WHERE user_id = ?";
        PreparedStatement statement = connector.getConnection().prepareStatement(query);
        statement.setString(1, userId);
        return statement.executeQuery();
    }

    public void updatePaymentStatus(String paymentId, String status) throws SQLException {
        String query = "UPDATE payments SET status = ? WHERE payment_id = ?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(query)) {
            statement.setString(1, status);
            statement.setString(2, paymentId);
            statement.executeUpdate();
            System.out.println("Payment status updated: " + paymentId + " -> " + status);
        }
    }

    public ResultSet getPaymentHistory(String userId, int limit) throws SQLException {
        String query = "SELECT * FROM payments WHERE user_id = ? ORDER BY created_at DESC LIMIT ?";
        PreparedStatement statement = connector.getConnection().prepareStatement(query);
        statement.setString(1, userId);
        statement.setInt(2, limit);
        return statement.executeQuery();
    }
}
