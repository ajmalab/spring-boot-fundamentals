package com.example.controller;

import com.example.service.FraudService;

public class AdminController {
    private FraudService fraudService;

    public AdminController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    public void handleReviewPendingReports() {
        System.out.println("AdminController: Handling review pending fraud reports request");
        fraudService.reviewPendingReports();
    }

    public void handleGetFraudReport(String reportId) {
        System.out.println("AdminController: Handling get fraud report request");
        fraudService.getFraudReport(reportId);
    }

    public void handleApproveFraudReport(String reportId) {
        System.out.println("AdminController: Handling approve fraud report request");
        fraudService.approveFraudReport(reportId);
    }

    public void handleRejectFraudReport(String reportId) {
        System.out.println("AdminController: Handling reject fraud report request");
        fraudService.rejectFraudReport(reportId);
    }

    public void handleFlagUser(String userId, String reason) {
        System.out.println("AdminController: Handling flag user request");
        fraudService.flagUserAccount(userId, reason);
    }

    public void handleViewFlaggedUsers() {
        System.out.println("AdminController: Handling view flagged users request");
        fraudService.viewFlaggedUsers();
    }

    public void handleGenerateFraudStatistics() {
        System.out.println("AdminController: Handling generate fraud statistics request");
        fraudService.generateFraudStatistics();
    }
}
