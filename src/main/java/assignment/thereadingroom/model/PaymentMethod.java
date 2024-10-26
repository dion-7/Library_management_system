package assignment.thereadingroom.model;

public abstract class PaymentMethod {
    public abstract boolean pay(double amount) throws Exception;
}
