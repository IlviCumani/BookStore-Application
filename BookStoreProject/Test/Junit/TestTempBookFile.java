package Junit;

import BookstoreData.Book;
import IO.BookFileIOService;
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
//        Book book1 = new Book("To Kill a Mockingbird", "9780061120084", "Harper Lee", "Fiction", "HarperCollins", 12.99, true);
//        Book book2 = new Book("Harry Potter 1", "9780061120087", "J.K Rowling", "Fiction", "HP", 10.99, true);
//        Book book3 = new Book("Art of War", "9780061120022", "Sun Tzu", "Philosophy", "EK", 6.99, false);
        Book book1 = new Book("The Catcher in the Rye", "9780316769488", "J.D. Salinger", "Fiction", "Little, Brown and Company", 15.99, true);

        Book book2 = new Book("To Kill a Mockingbird", "9780061120084", "Harper Lee", "Classics", "Harper Perennial Modern Classics", 12.50, true);

        Book book3 = new Book("1984", "9780451524935", "George Orwell", "Science Fiction", "Signet Classic", 9.99, true);

        Book book4 = new Book("The Great Gatsby", "9780743273565", "F. Scott Fitzgerald", "Classics", "Scribner", 10.25, true);

        Book book5 = new Book("The Hobbit", "9780345339683", "J.R.R. Tolkien", "Fantasy", "Del Rey", 8.99, true);

        Book book6 = new Book("Pride and Prejudice", "9780141439518", "Jane Austen", "Classics", "Penguin Classics", 7.99, true);

        Book book7 = new Book("Harry Potter and the Sorcerer's Stone", "9780590353427", "J.K. Rowling", "Fantasy", "Scholastic", 20.00, true);

        Book book8 = new Book("The Lord of the Rings", "9780544003415", "J.R.R. Tolkien", "Fantasy", "Mariner Books", 25.00, true);

        Book book9 = new Book("Brave New World", "9780060850524", "Aldous Huxley", "Science Fiction", "Harper Perennial Modern Classics", 11.99, true);

        Book book10 = new Book("The Alchemist", "9780062315007", "Paulo Coelho", "Fiction", "HarperOne", 14.99, true);

        Book[] books = {book1, book2, book3, book4, book5, book6, book7, book8, book9, book10};
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

    @Test
    void test_writeThisFile() {
        FileIO fileIO1 = new FileIO(new BookFileIOService());
        fileIO1.write(listOfBooks);
    }

    @Test
    void test_writeNullInFile() {
        FileIO fileIO1 = new FileIO();
        Assertions.assertThrows(RuntimeException.class, ()->{
            fileIO1.write(null);
        });
    }
}
