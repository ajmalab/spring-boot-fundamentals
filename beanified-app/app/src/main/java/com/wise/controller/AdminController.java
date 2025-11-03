package com.wise.controller;

import com.wise.dto.FraudReportDto;
import com.wise.dto.FraudStatisticsDto;
import com.wise.service.FraudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    //TODO: "Ask" for the FraudService bean
    private final FraudService fraudService;


    @GetMapping("/fraud-reports/pending")
    public ResponseEntity<List<FraudReportDto>> handleReviewPendingReports() {
        System.out.println("AdminController: Handling review pending fraud reports request");
        List<FraudReportDto> reports = fraudService.reviewPendingReports();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/fraud-reports/{reportId}")
    public ResponseEntity<FraudReportDto> handleGetFraudReport(@PathVariable String reportId) {
        System.out.println("AdminController: Handling get fraud report request");
        FraudReportDto report = fraudService.getFraudReport(reportId);
        if (report != null) {
            return ResponseEntity.ok(report);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/fraud-reports/{reportId}/approve")
    public ResponseEntity<FraudReportDto> handleApproveFraudReport(@PathVariable String reportId) {
        System.out.println("AdminController: Handling approve fraud report request");
        FraudReportDto report = fraudService.approveFraudReport(reportId);
        return ResponseEntity.ok(report);
    }

    @PostMapping("/fraud-reports/{reportId}/reject")
    public ResponseEntity<FraudReportDto> handleRejectFraudReport(@PathVariable String reportId) {
        System.out.println("AdminController: Handling reject fraud report request");
        FraudReportDto report = fraudService.rejectFraudReport(reportId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/fraud-reports/statistics")
    public ResponseEntity<List<FraudStatisticsDto>> handleGenerateFraudStatistics() {
        System.out.println("AdminController: Handling generate fraud statistics request");
        List<FraudStatisticsDto> statistics = fraudService.generateFraudStatistics();
        return ResponseEntity.ok(statistics);
    }
}
