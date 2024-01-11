package BookstoreData;

public class MOCKBook implements IsBook{
    private String isbn13;
    private double price;

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    private int copies;

    public MOCKBook(String isbn13, double price, int copies) {
        this.isbn13 = isbn13;
        this.price = price;
        this.copies = copies;
    }

    public MOCKBook() {

    }




}
