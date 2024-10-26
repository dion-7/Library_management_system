package assignment.thereadingroom.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CreditCardPaymentTest {

    private CreditCardPayment payment;

    @BeforeEach
    void setUp() {
        // Initialize CreditCardPayment before each test
        payment = new CreditCardPayment();
    }

    @Test
    void testValidCardNumber() {
        assertTrue(CreditCardPayment.isValidCardNumber("1234567812345678"));
        assertFalse(CreditCardPayment.isValidCardNumber("12345678"));
        assertFalse(CreditCardPayment.isValidCardNumber(null));
    }

    @Test
    void testValidCvv() {
        assertTrue(CreditCardPayment.isValidCvv("123"));
        assertFalse(CreditCardPayment.isValidCvv("12"));
        assertFalse(CreditCardPayment.isValidCvv(null));
    }

    @Test
    void testValidExpiryDate() {
        assertTrue(CreditCardPayment.isValidExpiryDate("12/25")); // Assuming current date is before December 2025
        assertFalse(CreditCardPayment.isValidExpiryDate("01/20")); // Assuming current date is after January 2020
        assertFalse(CreditCardPayment.isValidExpiryDate("invalid")); // Invalid format
    }

    @Test
    void testValidCardDetails() {
        payment.setCardNumber("1234567812345678");
        payment.setCvv("123");
        payment.setExpiryDate("12/25");
        assertTrue(payment.validateCardDetails());
    }

    @Test
    void testInvalidCardDetails() {
        payment.setCardNumber("123456781234567");
        payment.setCvv("12");
        payment.setExpiryDate("01/20"); // Assuming current date is after January 2020
        assertFalse(payment.validateCardDetails());
    }

    @Test
    void testPay() throws Exception {
        payment.setCardNumber("1234567812345678");
        payment.setCvv("123");
        payment.setExpiryDate("12/25");
        assertTrue(payment.pay(100.0));

        // Testing with invalid card details
        payment.setCardNumber("123456781234567");
        assertFalse(payment.pay(100.0));
    }
}
