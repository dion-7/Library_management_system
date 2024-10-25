package assignment.thereadingroom.model;

public class Order {
    private String id;
    private String username;
    private String createdAt;
    private double totalPrice;

    public Order() {}
    public Order(String orderId, String username, String createdAt, double totalPrice) {
        setId(orderId);
        setUsername(username);
        setCreatedAt(createdAt);
        setTotalPrice(totalPrice);
    }

    public String getId() {
        return id;
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

    public void setId(String orderId) {
        this.id = orderId;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
