package Junit;

import BookstoreData.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBook {

    @Test
    void test_get100PercentLineCoverageInBookClass(){
        Book book = new Book("To Kill a Mockingbird", "9780061120084", "Harper Lee", "Fiction", "HarperCollins", 12.99, true);
        book.setBookTitle("New Title");
        book.setBookAuthor("New Author");
        book.setBookGenre("New Genre");
        book.setBookPublisher("New Publisher");
        book.setBookPrice(100);
        book.setBookISBN13("9781234567890");
        book.setPaperback(false);
        book.setNrBookInStock(101);
        assertAll("Check if the book was edited",
                () -> assertEquals("New Title", book.getBookTitle()),
                () -> assertEquals("New Author", book.getBookAuthor()),
                () -> assertEquals("New Genre", book.getBookGenre()),
                () -> assertEquals("New Publisher", book.getBookPublisher()),
                () -> assertEquals(100, book.getBookPrice()),
                () -> assertEquals("9781234567890", book.getBookISBN13()),
                () -> assertFalse(book.getPaperback()),
                () -> assertEquals(101, book.getNrBookInStock()),
                () -> assertNotNull(book.toString())
        );
    }
}
