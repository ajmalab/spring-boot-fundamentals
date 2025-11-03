package com.wise.service;

import com.wise.dto.FraudReportDto;
import com.wise.dto.FraudStatisticsDto;
import com.wise.repository.FraudReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FraudService {
    private final FraudReportRepository fraudReportRepository;

    @Autowired
    public FraudService(FraudReportRepository fraudReportRepository) {
        this.fraudReportRepository = fraudReportRepository;
    }

    public FraudReportDto reportFraudulentActivity(String userId, String activityType, String description) {
        try {
            fraudReportRepository.reportFraudulentActivity(userId, activityType, description);
            System.out.println("FraudService: Fraudulent activity reported successfully");
            return new FraudReportDto(null, userId, activityType, description, "PENDING", new java.sql.Timestamp(System.currentTimeMillis()));
        } catch (SQLException e) {
            System.err.println("FraudService: Error reporting fraudulent activity - " + e.getMessage());
            throw new RuntimeException("Failed to report fraudulent activity: " + e.getMessage(), e);
        }
    }

    public FraudReportDto getFraudReport(String reportId) {
        try {
            ResultSet rs = fraudReportRepository.getFraudReport(reportId);
            if (rs.next()) {
                return new FraudReportDto(
                    rs.getLong("report_id"),
                    rs.getString("user_id"),
                    rs.getString("activity_type"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("FraudService: Error retrieving fraud report - " + e.getMessage());
            throw new RuntimeException("Failed to retrieve fraud report: " + e.getMessage(), e);
        }
    }

    public List<FraudReportDto> reviewPendingReports() {
        try {
            ResultSet rs = fraudReportRepository.getAllPendingFraudReports();
            List<FraudReportDto> reports = new ArrayList<>();
            while (rs.next()) {
                reports.add(new FraudReportDto(
                    rs.getLong("report_id"),
                    rs.getString("user_id"),
                    rs.getString("activity_type"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at")
                ));
            }
            return reports;
        } catch (SQLException e) {
            System.err.println("FraudService: Error reviewing pending reports - " + e.getMessage());
            throw new RuntimeException("Failed to review pending reports: " + e.getMessage(), e);
        }
    }

    public FraudReportDto approveFraudReport(String reportId) {
        try {
            fraudReportRepository.updateFraudReportStatus(reportId, "APPROVED");
            System.out.println("FraudService: Fraud report approved");
            return getFraudReport(reportId);
        } catch (SQLException e) {
            System.err.println("FraudService: Error approving fraud report - " + e.getMessage());
            throw new RuntimeException("Failed to approve fraud report: " + e.getMessage(), e);
        }
    }

    public FraudReportDto rejectFraudReport(String reportId) {
        try {
            fraudReportRepository.updateFraudReportStatus(reportId, "REJECTED");
            System.out.println("FraudService: Fraud report rejected");
            return getFraudReport(reportId);
        } catch (SQLException e) {
            System.err.println("FraudService: Error rejecting fraud report - " + e.getMessage());
            throw new RuntimeException("Failed to reject fraud report: " + e.getMessage(), e);
        }
    }

    public List<FraudStatisticsDto> generateFraudStatistics() {
        try {
            ResultSet rs = fraudReportRepository.getFraudStatistics();
            List<FraudStatisticsDto> statistics = new ArrayList<>();
            while (rs.next()) {
                statistics.add(new FraudStatisticsDto(
                    rs.getString("activity_type"),
                    rs.getInt("count"),
                    rs.getString("status")
                ));
            }
            return statistics;
        } catch (SQLException e) {
            System.err.println("FraudService: Error generating fraud statistics - " + e.getMessage());
            throw new RuntimeException("Failed to generate fraud statistics: " + e.getMessage(), e);
        }
    }
}
