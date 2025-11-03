package com.wise.service;

import com.wise.dto.PaymentDto;
import com.wise.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentDto recordPayment(String userId, double amount, String paymentMethod) {
        try {
            paymentRepository.savePayment(userId, amount, paymentMethod, "COMPLETED");
            System.out.println("PaymentService: Payment recorded successfully");
            // Return the newly created payment (in a real app, you'd get the ID from the insert)
            return new PaymentDto(null, userId, java.math.BigDecimal.valueOf(amount), paymentMethod, "COMPLETED", new java.sql.Timestamp(System.currentTimeMillis()));
        } catch (SQLException e) {
            System.err.println("PaymentService: Error recording payment - " + e.getMessage());
            throw new RuntimeException("Failed to record payment: " + e.getMessage(), e);
        }
    }

    public PaymentDto getPayment(String paymentId) {
        try {
            ResultSet rs = paymentRepository.getPayment(paymentId);
            if (rs.next()) {
                return new PaymentDto(
                    rs.getLong("payment_id"),
                    rs.getString("user_id"),
                    rs.getBigDecimal("amount"),
                    rs.getString("payment_method"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("PaymentService: Error retrieving payment - " + e.getMessage());
            throw new RuntimeException("Failed to retrieve payment: " + e.getMessage(), e);
        }
    }

    public List<PaymentDto> getUserPayments(String userId) {
        try {
            ResultSet rs = paymentRepository.getPaymentsByUser(userId);
            List<PaymentDto> payments = new ArrayList<>();
            while (rs.next()) {
                payments.add(new PaymentDto(
                    rs.getLong("payment_id"),
                    rs.getString("user_id"),
                    rs.getBigDecimal("amount"),
                    rs.getString("payment_method"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at")
                ));
            }
            return payments;
        } catch (SQLException e) {
            System.err.println("PaymentService: Error retrieving user payments - " + e.getMessage());
            throw new RuntimeException("Failed to retrieve user payments: " + e.getMessage(), e);
        }
    }

    public void updatePaymentStatus(String paymentId, String status) {
        try {
            paymentRepository.updatePaymentStatus(paymentId, status);
            System.out.println("PaymentService: Payment status updated successfully");
        } catch (SQLException e) {
            System.err.println("PaymentService: Error updating payment status - " + e.getMessage());
            throw new RuntimeException("Failed to update payment status: " + e.getMessage(), e);
        }
    }

    public List<PaymentDto> getPaymentHistory(String userId, int limit) {
        try {
            ResultSet rs = paymentRepository.getPaymentHistory(userId, limit);
            List<PaymentDto> payments = new ArrayList<>();
            while (rs.next()) {
                payments.add(new PaymentDto(
                    rs.getLong("payment_id"),
                    rs.getString("user_id"),
                    rs.getBigDecimal("amount"),
                    rs.getString("payment_method"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at")
                ));
            }
            return payments;
        } catch (SQLException e) {
            System.err.println("PaymentService: Error retrieving payment history - " + e.getMessage());
            throw new RuntimeException("Failed to retrieve payment history: " + e.getMessage(), e);
        }
    }

    public PaymentDto processRefund(String paymentId) {
        try {
            paymentRepository.updatePaymentStatus(paymentId, "REFUNDED");
            System.out.println("PaymentService: Refund processed for payment " + paymentId);
            return getPayment(paymentId);
        } catch (SQLException e) {
            System.err.println("PaymentService: Error processing refund - " + e.getMessage());
            throw new RuntimeException("Failed to process refund: " + e.getMessage(), e);
        }
    }
}
