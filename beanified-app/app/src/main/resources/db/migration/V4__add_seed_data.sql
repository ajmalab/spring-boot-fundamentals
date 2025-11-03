-- Insert seed data for users
INSERT INTO users (user_id, flagged, flag_reason, created_at) VALUES
('user001', FALSE, NULL, CURRENT_TIMESTAMP),
('user002', FALSE, NULL, CURRENT_TIMESTAMP),
('user003', TRUE, 'Multiple failed payment attempts', CURRENT_TIMESTAMP),
('user004', FALSE, NULL, CURRENT_TIMESTAMP),
('user005', TRUE, 'Suspicious transaction pattern detected', CURRENT_TIMESTAMP),
('user006', FALSE, NULL, CURRENT_TIMESTAMP);

-- Insert seed data for payments
INSERT INTO payments (user_id, amount, payment_method, status, created_at) VALUES
('user001', 99.99, 'CREDIT_CARD', 'COMPLETED', CURRENT_TIMESTAMP - INTERVAL '5' DAY),
('user001', 149.50, 'PAYPAL', 'COMPLETED', CURRENT_TIMESTAMP - INTERVAL '3' DAY),
('user001', 29.99, 'CREDIT_CARD', 'COMPLETED', CURRENT_TIMESTAMP - INTERVAL '1' DAY),
('user002', 199.99, 'DEBIT_CARD', 'COMPLETED', CURRENT_TIMESTAMP - INTERVAL '4' DAY),
('user002', 49.99, 'CREDIT_CARD', 'PENDING', CURRENT_TIMESTAMP - INTERVAL '2' HOUR),
('user003', 299.99, 'CREDIT_CARD', 'FAILED', CURRENT_TIMESTAMP - INTERVAL '6' DAY),
('user003', 299.99, 'CREDIT_CARD', 'FAILED', CURRENT_TIMESTAMP - INTERVAL '6' DAY),
('user003', 299.99, 'PAYPAL', 'FAILED', CURRENT_TIMESTAMP - INTERVAL '5' DAY),
('user004', 79.99, 'CREDIT_CARD', 'COMPLETED', CURRENT_TIMESTAMP - INTERVAL '2' DAY),
('user004', 129.99, 'DEBIT_CARD', 'REFUNDED', CURRENT_TIMESTAMP - INTERVAL '1' DAY),
('user005', 999.99, 'CREDIT_CARD', 'COMPLETED', CURRENT_TIMESTAMP - INTERVAL '7' DAY),
('user005', 1499.99, 'CREDIT_CARD', 'COMPLETED', CURRENT_TIMESTAMP - INTERVAL '7' DAY),
('user005', 2999.99, 'CREDIT_CARD', 'COMPLETED', CURRENT_TIMESTAMP - INTERVAL '6' DAY),
('user006', 19.99, 'PAYPAL', 'COMPLETED', CURRENT_TIMESTAMP - INTERVAL '1' DAY);

-- Insert seed data for fraud reports
INSERT INTO fraud_reports (user_id, activity_type, description, status, created_at) VALUES
('user003', 'PAYMENT_FRAUD', 'Multiple failed payment attempts with different cards', 'APPROVED', CURRENT_TIMESTAMP - INTERVAL '5' DAY),
('user005', 'SUSPICIOUS_PATTERN', 'Unusually large transactions in short time period', 'PENDING', CURRENT_TIMESTAMP - INTERVAL '6' DAY),
('user005', 'HIGH_VALUE_TRANSACTION', 'Transaction amount exceeds normal user behavior', 'PENDING', CURRENT_TIMESTAMP - INTERVAL '6' DAY),
('user002', 'ACCOUNT_TAKEOVER', 'Login from unusual location', 'REJECTED', CURRENT_TIMESTAMP - INTERVAL '3' DAY),
('user006', 'CHARGEBACK', 'Customer initiated chargeback', 'PENDING', CURRENT_TIMESTAMP - INTERVAL '12' HOUR);
