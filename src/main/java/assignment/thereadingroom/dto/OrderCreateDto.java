package assignment.thereadingroom.dto;

import assignment.thereadingroom.utils.Helpers;

import java.util.UUID;

public class OrderCreateDto {
    private String id;
    private String username;
    private double totalPrice;
    private String createdAt;

    private void setAutoFields(){
        this.id = UUID.randomUUID().toString();
        this.createdAt = Helpers.getCurrentDateTimeIsoString();
    }

    public OrderCreateDto() {
        setAutoFields();
    }

    public OrderCreateDto(String username, double totalPrice) {
        setAutoFields();
        setUsername(username);
        setTotalPrice(totalPrice);
    }

    public String getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getUsername() {
        return username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
