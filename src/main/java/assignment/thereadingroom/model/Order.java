package assignment.thereadingroom.model;

public class Order {
    private String orderId;
    private String username;
    private String createdAt;
    private double totalPrice;

    public Order() {}
    public Order(int orderId, String username, String createdAt, double totalPrice) {}

    public String getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
