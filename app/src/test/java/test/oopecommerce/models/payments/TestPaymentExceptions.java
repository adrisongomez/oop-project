package test.oopecommerce.models.payments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.oopecommerce.exceptions.PaymentFailedException;
import com.oopecommerce.models.payments.CreditCardPayment;
import com.oopecommerce.models.payments.PaymentProcessor.PaymentStatus;

public class TestPaymentExceptions {
    @Test
    public void confirmPaymentThrowsOnFailure() {
        CreditCardPayment payment = new CreditCardPayment();
        payment.initiatePayment(10.0);
        try {
            java.lang.reflect.Field f = payment.getClass().getDeclaredField("status");
            f.setAccessible(true);
            f.set(payment, PaymentStatus.FAILURE);
        } catch (Exception e) {
            fail("Reflection error" + e.getMessage());
        }

        assertThrows(PaymentFailedException.class, payment::confirmPayment);
    }
}
