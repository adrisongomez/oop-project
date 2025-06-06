package com.oopecommerce.models.payments;

public interface PaymentProcessor {
    
    enum PaymentStatus {
        SUCCESS,
        FAILURE,
        PENDING
    }

    void initiatePayment(double amount);

    PaymentStatus verifyPayment();

    void confirmPayment();
} 