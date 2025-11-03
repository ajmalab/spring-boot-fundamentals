package com.example;

import com.example.database.DatabaseConnector;
import com.example.repository.PaymentRepository;
import com.example.repository.FraudReportRepository;
import com.example.service.PaymentService;
import com.example.service.FraudService;
import com.example.controller.UserController;
import com.example.controller.AdminController;

public class App {
    public static void main(String[] args) {


        // Initialize Controllers with their respective services
        // UserController userController = new UserController(paymentService);
        // AdminController adminController = new AdminController(fraudService);

        System.out.println("Application initialized.");
    }
}
