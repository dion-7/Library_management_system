package assignment.thereadingroom.model;

public class OrderItem {
    private String id;
    private String orderId;
    private int quantity;
    private double price;
    private String bookTitle;

    public OrderItem() {}
    public OrderItem(String id, String orderId, String bookTitle, int quantity, double price) {
        setId(id);
        setOrderId(orderId);
        setBookTitle(bookTitle);
        setQuantity(quantity);
        setPrice(price);

    }

    // Getters

    public String getId() {
        return id;
    }
    public String getOrderId() {
        return orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    // Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

}
