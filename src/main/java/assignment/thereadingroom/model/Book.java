package assignment.thereadingroom.model;

public class Book {
    private String title;
    private String authors;
    private int nPhysicalCopies;
    private float price;
    private int nSoldCopies;

    public Book() {}

    public Book(String title, String authors, int nPhysicalCopies, float price, int nSoldCopies) {
        this.title = title;
        this.authors = authors;
        this.nPhysicalCopies = nPhysicalCopies;
        this.price = price;
        this.nSoldCopies = nSoldCopies;
    }

    // Getters

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public int getNPhysicalCopies() {
        return nPhysicalCopies;
    }

    public float getPrice()  {
        return price;
    }

    public int getNSoldCopies() {
        return nSoldCopies;
    }

    // Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setNPhysicalCopies(int nPhysicalCopies) {
        this.nPhysicalCopies = nPhysicalCopies;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setNSoldCopies(int nSoldCopies) {
        this.nSoldCopies = nSoldCopies;
    }
}
