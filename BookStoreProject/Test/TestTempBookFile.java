import BookstoreData.Book;
import IO.TEMPBookFileIOService;
import IO.FileIO;
import IO.FileIOServiceInjectable;
import StaffFolder.AccessLevels.Administrator;
import StaffFolder.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestTempBookFile {

    private FileIOServiceInjectable fileIoService;
    private ArrayList<Serializable> listOfBooks;
    private Worker admin;
     private TEMPBookFileIOService bookFileIOService;
     private FileIO fileIO;

    @BeforeEach
    void setUp() {
        this.admin = new Worker("admin", "admin", "12345678", "0675850510", null, new Administrator(), 1000);
        Book book1 = new Book("To Kill a Mockingbird", "9780061120084", "Harper Lee", "Fiction", "HarperCollins", 12.99, true);
        Book book2 = new Book("Harry Potter 1", "9780061120087", "J.K Rowling", "Fiction", "HP", 10.99, true);
        Book book3 = new Book("Art of War", "9780061120022", "Sun Tzu", "Philosophy", "EK", 6.99, false);
        Book[] books = {book1, book2, book3};
        listOfBooks = new ArrayList<>();
        listOfBooks.addAll(List.of(books));
        this.bookFileIOService = new TEMPBookFileIOService();
        this.fileIO = new FileIO(bookFileIOService);
    }

    @Test
    void test_writeAndReadOfbooks() {
        this.fileIO.write(listOfBooks);
        Assertions.assertEquals(listOfBooks, fileIO.read());
    }

    @Test
    void test_WriteAndReadOfBooksAfterAddingADummyBook() {
        this.fileIO.write(listOfBooks);
        listOfBooks.add(new Worker());
        fileIO.write(listOfBooks);
        Assertions.assertEquals(listOfBooks, fileIO.read());
    }

    @Test
    void test_WriteAndReadOfBooksAfterRemoving() {
        this.fileIO.write(listOfBooks);
        listOfBooks.removeFirst();
        this.fileIO.write(listOfBooks);
        Assertions.assertEquals(listOfBooks, fileIO.read());
    }
}
