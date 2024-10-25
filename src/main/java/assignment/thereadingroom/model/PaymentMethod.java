package assignment.thereadingroom.model;

public abstract class PaymentMethod {
    public abstract boolean pay(float amount) throws Exception;
}
