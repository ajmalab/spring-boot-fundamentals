package com.example.controller;

import com.example.service.PaymentService;

public class UserController {
    private PaymentService paymentService;

    public UserController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void handleMakePayment(String userId, double amount, String paymentMethod) {
        System.out.println("UserController: Handling payment request");
        paymentService.recordPayment(userId, amount, paymentMethod);
    }

    public void handleGetPayment(String paymentId) {
        System.out.println("UserController: Handling get payment request");
        paymentService.getPayment(paymentId);
    }

    public void handleViewPayments(String userId) {
        System.out.println("UserController: Handling view payments request");
        paymentService.getUserPayments(userId);
    }

    public void handleViewPaymentHistory(String userId, int limit) {
        System.out.println("UserController: Handling view payment history request");
        paymentService.getPaymentHistory(userId, limit);
    }

    public void handleRequestRefund(String paymentId) {
        System.out.println("UserController: Handling refund request");
        paymentService.processRefund(paymentId);
    }
}
