package Test;
import BookstoreData.Book;
import StaffFolder.AccessLevels.*;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.NoPermissionToAddNewBooks;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.PermissionToAddNewBook;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.PermissionToResupply;
import StaffFolder.AccessLevels.Behaviours.SellBooks.NoPermissionToSellBooks;
import StaffFolder.Worker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestWorker {
    Worker librarian;//new Worker("Lib", "librarian", "12345678", "0675850510", null, new Librarian(), 500);
    Worker manager;// = new Worker("manager", "manager", "12345678", "0675850510", null, new Manager(), 700);
    Worker admin; //= new Worker("admin", "admin", "12345678", "0675850510", null, new Administrator(), 1000);
    ArrayList<Book> bookList; //= new ArrayList<Book>();
    ArrayList<Worker> listOfWorkers;
    @BeforeEach
    void setUp() {
        this.librarian = new Worker("Lib", "librarian", "12345678", "0675850510", null, new Librarian(), 500);
        this.manager = new Worker("manager", "manager", "12345678", "0675850510", null, new Manager(), 700);
        this.admin = new Worker("admin", "admin", "12345678", "0675850510", null, new Administrator(), 1000);
        this.bookList = new ArrayList<>();
        this.listOfWorkers = new ArrayList<>();
        Worker[] workers = {librarian, manager, admin};
        this.listOfWorkers.addAll(List.of(workers));
    }

    @BeforeEach
    public void loadBooks() {
        bookList.add(new Book("To Kill a Mockingbird", "9780061120084", "Harper Lee", "Fiction", "HarperCollins", 12.99, true));
        bookList.add(new Book("1984", "9780451524935", "George Orwell", "Dystopian", "Signet Classics", 9.99, true));
        bookList.add(new Book("The Great Gatsby", "9780743273565", "F. Scott Fitzgerald", "Classics", "Scribner", 10.99, true));
        bookList.add(new Book("The Hobbit", "9780547928227", "J.R.R. Tolkien", "Fantasy", "Mariner Books", 14.99, true));
        bookList.add(new Book("To the Lighthouse", "9780156907392", "Virginia Woolf", "Modernist", "Harcourt Brace Jovanovich", 11.99, true));
        bookList.add(new Book("The Catcher in the Rye", "9780241950425", "J.D. Salinger", "Coming of Age", "Penguin Books", 13.99, true));
        bookList.add(new Book("The Alchemist", "9780061122415", "Paulo Coelho", "Fiction", "HarperOne", 10.99, true));
        bookList.add(new Book("The Da Vinci Code", "9780307474278", "Dan Brown", "Mystery", "Anchor Books", 15.99, true));
        bookList.add(new Book("The Girl on the Train", "9781594634024", "Paula Hawkins", "Thriller", "Riverhead Books", 12.99, true));
        bookList.add(new Book("Sapiens: A Brief History of Humankind", "9780062316097", "Yuval Noah Harari", "History", "Harper", 16.99, true));
    }

    @Test
    public void test_LibrarianWithPermissionToSellBooks() {
//        loadBooks();
        librarian.getAccessLevel().sellBooks(bookList.get(0), 30);
        assertEquals(70, bookList.get(0).getNrBookInStock());
    }

    @Test
    public void test_LibrarianWithoutPermissionToSellBooks() {
//        loadBooks();
        librarian.getAccessLevel().setSellBooksBehaviour(new NoPermissionToSellBooks());
        assertThrows(IllegalStateException.class, () -> {
            librarian.getAccessLevel().sellBooks(bookList.get(0), 30);
        });
    }

    @Test
    public void test_LibrarianResupplyBooks() {
//        loadBooks();
        librarian.getAccessLevel().setResupplyStockBehaviour(new PermissionToResupply());
        assertThrows(IllegalStateException.class, () -> {
            librarian.getAccessLevel().resupplyStock(bookList.get(0), 30);
        });
    }

    @Test
    public void test_SellingMoreBooksThanInStock() {
//        loadBooks();
        assertThrows(IllegalArgumentException.class, () -> {
            librarian.getAccessLevel().sellBooks(bookList.get(0), 130);
        });
    }

    @Test
    public void test_ManagerResupplyBooks() {
        loadBooks();
        manager.getAccessLevel().resupplyStock(bookList.get(0), 30);
        assertEquals(130, bookList.get(0).getNrBookInStock());
    }

    @Test
    public void test_ManagerAddNewBook(){
//        loadBooks();
        Book newBook = manager.getAccessLevel().addNewBook("The Lord of the Rings", "9780545010221", "J.R.R. Tolkien", "Fantasy", "Mariner Books", 29.99, false);
        bookList.add(newBook);
        assertEquals(11, bookList.size());
    }

    @Test
    public void test_ManagerNoPermissionToAddNewBook() {
//        loadBooks();
        manager.getAccessLevel().setAddNewBooksBehaviour(new NoPermissionToAddNewBooks());
        assertThrows(IllegalStateException.class, () -> {
            manager.getAccessLevel().addNewBook("The Lord of the Rings", "9780545010221", "J.R.R. Tolkien", "Fantasy", "Mariner Books", 29.99, false);
        });
    }

    @Test
    public void test_addNewBookWithInvalidISBN() {
//        loadBooks();
        assertThrows(IllegalArgumentException.class, () -> {
            manager.getAccessLevel().addNewBook("The Lord of the Rings", "Hello Friend", "J.R.R. Tolkien", "Fantasy", "Mariner Books", 29.99, false);
        });
    }

    @Test
    public void test_addNewBookWithInvalidPrice(){
//        loadBooks();
        assertThrows(IllegalArgumentException.class, () -> {
            manager.getAccessLevel().addNewBook("The Lord of the Rings", "9780545010221", "J.R.R. Tolkien", "Fantasy", "Mariner Books", -29.99, false);
        });
    }

    @Test
    public void test_adminFiresLibrarianWorker() throws PermissionDeniedException {
        assertTrue(admin.getAccessLevel().fireWorker(listOfWorkers, librarian));
    }

    @Test
    public void test_adminFirestManagerWorker() throws PermissionDeniedException {
        assertTrue(admin.getAccessLevel().fireWorker(listOfWorkers, manager));
    }

    @Test
    public void test_adminFirestAnotherAdminWorker(){
        assertThrows(IllegalArgumentException.class, () -> {
           admin.getAccessLevel().fireWorker(listOfWorkers, admin);
        });
    }

    @Test
    public void test_adminFiresAWorkerThatIsNotInTheList() throws PermissionDeniedException{
        assertFalse(admin.getAccessLevel().fireWorker(listOfWorkers, new Worker()));
    }

    @Test
    public void test_managerFirestAWorker() {
        assertThrows(PermissionDeniedException.class, () -> {
           manager.getAccessLevel().fireWorker(listOfWorkers, librarian);
        });
    }

    @Test
    public void test_librarianFirestAWorker() {
        assertThrows(PermissionDeniedException.class, () -> {
            librarian.getAccessLevel().fireWorker(listOfWorkers, manager);
        });
    }

    @Test
    public void test_adminAddsNewWorker() throws PermissionDeniedException{
        int oldListSize = listOfWorkers.size();
        admin.getAccessLevel().addNewWorker(listOfWorkers, new Worker());
        int newListSize = listOfWorkers.size();
        assertEquals(oldListSize + 1, newListSize);
    }

    @Test
    public void test_managerAddsNewWorker() {
        assertThrows(PermissionDeniedException.class, () -> {
            manager.getAccessLevel().addNewWorker(listOfWorkers, new Worker());
        });
    }

    @Test
    public void test_librarianAddsNewWorker() {
        assertThrows(PermissionDeniedException.class, () -> {
            librarian.getAccessLevel().addNewWorker(listOfWorkers, new Worker());
        });
    }





}
