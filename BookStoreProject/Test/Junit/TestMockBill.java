package Junit;

import BookstoreData.Book;
import IO.bill.MOCKBillWriteFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class TestMockBill {

    HashMap<Book, Integer> dictOfBooks = new HashMap<>();
    Book book1 = new Book("The Catcher in the Rye", "9780316769488", "J.D. Salinger", "Fiction", "Little, Brown and Company", 15.99, true);

    Book book2 = new Book("To Kill a Mockingbird", "9780061120084", "Harper Lee", "Classics", "Harper Perennial Modern Classics", 12.50, true);

    Book book3 = new Book("1984", "9780451524935", "George Orwell", "Science Fiction", "Signet Classic", 9.99, true);

    MOCKBillWriteFile mock;
    @BeforeEach
    void setUp() {
//        Book book1 = new Book("The Catcher in the Rye", "9780316769488", "J.D. Salinger", "Fiction", "Little, Brown and Company", 15.99, true);
//
//        Book book2 = new Book("To Kill a Mockingbird", "9780061120084", "Harper Lee", "Classics", "Harper Perennial Modern Classics", 12.50, true);
//
//        Book book3 = new Book("1984", "9780451524935", "George Orwell", "Science Fiction", "Signet Classic", 9.99, true);

        Book book4 = new Book("The Great Gatsby", "9780743273565", "F. Scott Fitzgerald", "Classics", "Scribner", 10.25, true);

        Book book5 = new Book("The Hobbit", "9780345339683", "J.R.R. Tolkien", "Fantasy", "Del Rey", 8.99, true);

        Book book6 = new Book("Pride and Prejudice", "9780141439518", "Jane Austen", "Classics", "Penguin Classics", 7.99, true);

        Book book7 = new Book("Harry Potter and the Sorcerer's Stone", "9780590353427", "J.K. Rowling", "Fantasy", "Scholastic", 20.00, true);

        Book book8 = new Book("The Lord of the Rings", "9780544003415", "J.R.R. Tolkien", "Fantasy", "Mariner Books", 25.00, true);

        Book book9 = new Book("Brave New World", "9780060850524", "Aldous Huxley", "Science Fiction", "Harper Perennial Modern Classics", 11.99, true);

        Book book10 = new Book("The Alchemist", "9780062315007", "Paulo Coelho", "Fiction", "HarperOne", 14.99, true);
        dictOfBooks.put(book1, 5);
        dictOfBooks.put(book2, 10);
        dictOfBooks.put(book3, 20);


    }

    @Test
    void test_billIsCalculatedCorrectly() {

        double outTotal = 0 ;
        for (Book b: dictOfBooks.keySet()) {
            outTotal += b.getBookPrice() * dictOfBooks.get(b);
        }
        mock = new MOCKBillWriteFile(outTotal);
        Assertions.assertTrue(mock.write(dictOfBooks));
    }
}
