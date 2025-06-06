package com.oopecommerce.models.payments;

public class PayPalPayment implements PaymentProcessor {
    private PaymentStatus status = PaymentStatus.PENDING;
    private boolean userRedirected = false;

    @Override
    public void initiatePayment(double amount) {
        System.out.println("Redirecting user to PayPal for a payment of $" + amount + "...");
        // Simulate the user being redirected and approving the payment.
        this.userRedirected = true;
    }

    @Override
    public PaymentStatus verifyPayment() {
        if (userRedirected) {
            System.out.println("Verifying PayPal transaction...");
            this.status = PaymentStatus.SUCCESS; // Assume user approved.
        } else {
            this.status = PaymentStatus.FAILURE;
        }
        return this.status;
    }

    @Override
    public void confirmPayment() {
        if (this.status == PaymentStatus.SUCCESS) {
            System.out.println("PayPal payment successfully confirmed.");
        } else {
            System.out.println("PayPal payment failed or was not initiated.");
        }
    }
} 