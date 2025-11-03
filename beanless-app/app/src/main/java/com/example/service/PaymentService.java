package com.example.service;

import com.example.repository.PaymentRepository;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void recordPayment(String userId, double amount, String paymentMethod) {
        try {
            paymentRepository.savePayment(userId, amount, paymentMethod, "COMPLETED");
            System.out.println("PaymentService: Payment recorded successfully");
        } catch (SQLException e) {
            System.err.println("PaymentService: Error recording payment - " + e.getMessage());
        }
    }

    public void getPayment(String paymentId) {
        try {
            ResultSet rs = paymentRepository.getPayment(paymentId);
            if (rs.next()) {
                System.out.println("PaymentService: Found payment - ID: " + rs.getString("payment_id") +
                                 ", Amount: " + rs.getDouble("amount") +
                                 ", Status: " + rs.getString("status"));
            } else {
                System.out.println("PaymentService: Payment not found");
            }
        } catch (SQLException e) {
            System.err.println("PaymentService: Error retrieving payment - " + e.getMessage());
        }
    }

    public void getUserPayments(String userId) {
        try {
            ResultSet rs = paymentRepository.getPaymentsByUser(userId);
            System.out.println("PaymentService: Retrieving payments for user " + userId);
            while (rs.next()) {
                System.out.println("Payment ID: " + rs.getString("payment_id") +
                                 ", Amount: " + rs.getDouble("amount") +
                                 ", Method: " + rs.getString("payment_method"));
            }
        } catch (SQLException e) {
            System.err.println("PaymentService: Error retrieving user payments - " + e.getMessage());
        }
    }

    public void updatePaymentStatus(String paymentId, String status) {
        try {
            paymentRepository.updatePaymentStatus(paymentId, status);
            System.out.println("PaymentService: Payment status updated successfully");
        } catch (SQLException e) {
            System.err.println("PaymentService: Error updating payment status - " + e.getMessage());
        }
    }

    public void getPaymentHistory(String userId, int limit) {
        try {
            ResultSet rs = paymentRepository.getPaymentHistory(userId, limit);
            System.out.println("PaymentService: Retrieving payment history for user " + userId);
            while (rs.next()) {
                System.out.println("Payment: " + rs.getString("payment_id") +
                                 ", Amount: " + rs.getDouble("amount") +
                                 ", Date: " + rs.getTimestamp("created_at"));
            }
        } catch (SQLException e) {
            System.err.println("PaymentService: Error retrieving payment history - " + e.getMessage());
        }
    }

    public void processRefund(String paymentId) {
        try {
            paymentRepository.updatePaymentStatus(paymentId, "REFUNDED");
            System.out.println("PaymentService: Refund processed for payment " + paymentId);
        } catch (SQLException e) {
            System.err.println("PaymentService: Error processing refund - " + e.getMessage());
        }
    }
}
