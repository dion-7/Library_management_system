package assignment.thereadingroom.model;

public class CartItem {
    private int quantity;
    private float price;
    private Book book;

    private void updatePrice() {
        if(this.book != null) {
            this.price = this.quantity * this.book.getPrice();
        }
        else {
            this.price = 0;
        }
    }

    public CartItem() {}

    public CartItem(Book book, int quantity)  {
        setBook(book);
        setQuantity(quantity);
    }

    // Getters
    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Book getBook() {
        return book;
    }

    // Setters

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (quantity > book.getNPhysicalCopies()){
            throw new IllegalArgumentException("Quantity cannot be greater than the number of physical copies");
        }

        this.quantity = quantity;
        this.updatePrice();
    }

    public void setBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        this.book = book;
    }
}
