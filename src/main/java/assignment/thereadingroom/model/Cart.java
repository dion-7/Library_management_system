package assignment.thereadingroom.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private static Cart instance;

    private List<CartItem> items;

    // Find cart item by book
    private Optional<CartItem> findCartItemByBook(Book book) {
        return items.stream()
                .filter(cartItem -> cartItem.getBook().equals(book))
                .findFirst();
    }

    private void _addBook(Book book, int quantity) {
        if(quantity < 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        Optional<CartItem> existingItem = findCartItemByBook(book);
        if(existingItem.isPresent()) {
            // If book is already in the cart, update the quantity
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem item = new CartItem(book, quantity);
            items.add(item);
        }
    }

    private Cart() {
        this.items = new ArrayList<CartItem>();
    }

    // Singleton instance getter
    public static Cart getCart(){
        if(instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    // =======================
    // Cart management methods
    // =======================
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void addBook(Book book) {
        _addBook(book, 1);
    }

    public void addBook(Book book, int quantity) {
        _addBook(book, quantity);
    }

    public void removeBook(Book book) {
        items.removeIf(cartItem -> cartItem.getBook().equals(book));
    }

    public void updateBookQuantity(Book book, int quantity) {
        Optional<CartItem> existingItem = findCartItemByBook(book);
        if(existingItem.isPresent()) {
            if (quantity <= 0) {
                removeBook(book);
            } else {
                existingItem.get().setQuantity(quantity);
            }
        } else {
            addBook(book, quantity);
        }

    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public void clearCart(){
        items.clear();
    }

    public double getTotalPrice(){
        double totalPrice = 0;
        for(CartItem item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

}
