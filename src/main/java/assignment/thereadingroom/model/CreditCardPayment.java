package assignment.thereadingroom.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CreditCardPayment extends PaymentMethod {
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public static boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("^[0-9]{16}$");
    }

    public static boolean isValidCvv(String cvv) {
        return cvv != null && cvv.matches("^[0-9]{3}$");
    }

    public static boolean isValidExpiryDate(String expiryDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        try{
            LocalDate expiry = LocalDate.parse("01/" + expiryDate, DateTimeFormatter.ofPattern("dd/MM/yy"));
            return expiry.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public CreditCardPayment() {}

    public CreditCardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        setCardNumber(cardNumber);
        setCvv(cvv);
        setExpiryDate(expiryDate);
    }

    public boolean isValidCardNumber() {
        return isValidCardNumber(this.cardNumber);
    }

    public boolean validateCardDetails(){
        return isValidCardNumber(this.cardNumber) && isValidCvv(this.cvv) && isValidExpiryDate(this.expiryDate);
    }


    @Override
    public boolean pay(double amount) throws Exception {
        // For now return true if the card details are valid
        return validateCardDetails();
    }

    // Getters
    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    // Setters
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
