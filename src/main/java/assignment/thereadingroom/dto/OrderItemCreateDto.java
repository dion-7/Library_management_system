package assignment.thereadingroom.dto;

import assignment.thereadingroom.model.Book;

import java.util.UUID;

public class OrderItemCreateDto {
    private String id;
    private String orderId;
    private int quantity;
    private double price;
    private String bookTitle;

    private void setId(){
        this.id = UUID.randomUUID().toString();
    }

    public OrderItemCreateDto() {
        setId();
    }

    public OrderItemCreateDto(String orderId, int quantity, double price, String bookTitle) {
        setId();
        setOrderId(orderId);
        setQuantity(quantity);
        setPrice(price);
        setBookTitle(bookTitle);
    }

    public OrderItemCreateDto(String orderId, int quantity, Book book) {
        setId();
        setOrderId(orderId);
        setQuantity(quantity);
        setBookData(book);
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

    public void setBookData(Book book) {
        this.bookTitle = book.getTitle();
        this.price = book.getPrice();
    }
}
