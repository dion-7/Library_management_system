package assignment.thereadingroom.model;

public class OrderJoinedItem {
    private String orderId;
    private String username;
    private String createdAt;
    private double totalPrice;
    private String itemId;
    private String bookTitle;
    private int quantity;
    private double itemPrice;

    public OrderJoinedItem(String orderId, String username, String createdAt, double totalPrice,
                           String itemId, String bookTitle, int quantity, double itemPrice) {
        this.orderId = orderId;
        this.username = username;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.itemId = itemId;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    // Getters and Setters for each field

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getItemPrice() { return itemPrice; }
    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }
}
