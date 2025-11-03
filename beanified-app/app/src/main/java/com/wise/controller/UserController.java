package com.wise.controller;

import com.wise.dto.PaymentDto;
import com.wise.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final PaymentService paymentService;
    private final ApplicationContext applicationContext;


    public UserController(@Autowired ApplicationContext applicationContext) {

        //TODO: Get the paymentService bean from the application context!
    }

    @PostMapping("/{userId}/payments")
    public ResponseEntity<PaymentDto> handleMakePayment(@PathVariable String userId,
                                                         @RequestParam double amount,
                                                         @RequestParam String paymentMethod) {
        System.out.println("UserController: Handling payment request");
        PaymentDto payment = paymentService.recordPayment(userId, amount, paymentMethod);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<PaymentDto> handleGetPayment(@PathVariable String paymentId) {
        System.out.println("UserController: Handling get payment request");
        PaymentDto payment = paymentService.getPayment(paymentId);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/payments")
    public ResponseEntity<List<PaymentDto>> handleViewPayments(@PathVariable String userId) {
        System.out.println("UserController: Handling view payments request");
        List<PaymentDto> payments = paymentService.getUserPayments(userId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{userId}/payments/history")
    public ResponseEntity<List<PaymentDto>> handleViewPaymentHistory(@PathVariable String userId,
                                                                      @RequestParam(defaultValue = "10") int limit) {
        System.out.println("UserController: Handling view payment history request");
        List<PaymentDto> payments = paymentService.getPaymentHistory(userId, limit);
        return ResponseEntity.ok(payments);
    }

    @PostMapping("/payments/{paymentId}/refund")
    public ResponseEntity<PaymentDto> handleRequestRefund(@PathVariable String paymentId) {
        System.out.println("UserController: Handling refund request");
        PaymentDto payment = paymentService.processRefund(paymentId);
        return ResponseEntity.ok(payment);
    }
}
