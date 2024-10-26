package assignment.thereadingroom.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        cart = Cart.getCart();
        book1 = new Book("Book Title 1", "Author 1", 10, 20.0f, 0);
        book2 = new Book("Book Title 2", "Author 2", 5, 15.0f, 0);
    }

    @Test
    void testAddBook() {
        cart.addBook(book1);
        List<CartItem> items = cart.getItems();
        assertEquals(1, items.size());
        assertEquals(book1, items.get(0).getBook());
        assertEquals(1, items.get(0).getQuantity());
    }

    @Test
    void removeBook() {
        cart.addBook(book1);
        cart.removeBook(book1);
        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    public void testAddMultipleBooks() {
        cart.addBook(book1, 3);
        assertEquals(3, cart.getItems().get(0).getQuantity());

        cart.addBook(book1, 2);
        assertEquals(5, cart.getItems().get(0).getQuantity()); // quantity should now be 5
    }

    @Test
    public void testUpdateBookQuantity() {
        cart.addBook(book1, 2);
        cart.updateBookQuantity(book1, 5);
        assertEquals(5, cart.getItems().get(0).getQuantity());

        cart.updateBookQuantity(book1, 0);
        assertTrue(cart.getItems().isEmpty()); // should remove the item if quantity is set to 0
    }

    @Test
    public void testTotalPrice() {
        cart.addBook(book1, 2); // 2 * 20.0 = 40.0
        cart.addBook(book2, 1); // 1 * 15.0 = 15.0
        assertEquals(55.0, cart.getTotalPrice(), 0.001); // 40.0 + 15.0 = 55.0
    }

    @Test
    public void testClearCart() {
        cart.addBook(book1);
        cart.clearCart();
        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    public void testAddBookNegativeQuantity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.addBook(book1, -1);
        });
        assertEquals("Quantity must be positive", exception.getMessage());
    }

    @Test
    public void testUpdateBookNegativeQuantity() {
        cart.addBook(book1, 2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.updateBookQuantity(book1, -1);
        });
        assertEquals("Quantity cannot be negative", exception.getMessage());
    }

    @Test
    public void testUpdateBookOverflowQuantity() {
        cart.addBook(book1, 2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.updateBookQuantity(book1, 30);
        });
        assertEquals("Quantity cannot be greater than the number of physical copies", exception.getMessage());
    }
}