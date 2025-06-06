package com.oopecommerce.models.payments;

public class CreditCardPayment implements PaymentProcessor {
    private PaymentStatus status;

    public CreditCardPayment() {
        this.status = PaymentStatus.PENDING;
    }

    @Override
    public void initiatePayment(double amount) {
        System.out.println("Initiating credit card payment of $" + amount + "...");
        // In a real scenario, this would connect to a payment gateway.
        this.status = PaymentStatus.SUCCESS; // Assume success for this mock.
    }

    @Override
    public PaymentStatus verifyPayment() {
        System.out.println("Verifying credit card payment...");
        return this.status;
    }

    @Override
    public void confirmPayment() {
        if (this.status == PaymentStatus.SUCCESS) {
            System.out.println("Credit card payment confirmed.");
        } else {
            System.out.println("Credit card payment could not be confirmed.");
        }
    }
} 