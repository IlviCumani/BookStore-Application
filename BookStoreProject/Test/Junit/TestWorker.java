package Junit;

import BookstoreData.Book;
import StaffFolder.AccessLevels.Administrator;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.NoPermissionToAddNewBooks;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.PermissionToResupply;
import StaffFolder.AccessLevels.Behaviours.SellBooks.NoPermissionToSellBooks;
import StaffFolder.AccessLevels.Librarian;
import StaffFolder.AccessLevels.Manager;
import StaffFolder.Worker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
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
    public void test_LibrarianWithPermissionToSellBooks() throws PermissionDeniedException {
        librarian.getAccessLevel().sellBooks(bookList.get(0), 30);
        assertEquals(70, bookList.get(0).getNrBookInStock());
    }

    @Test
    public void test_LibrarianWithoutPermissionToSellBooks() {
        librarian.getAccessLevel().setSellBookBehaviour(new NoPermissionToSellBooks());
        assertThrows(PermissionDeniedException.class, () -> {
            librarian.getAccessLevel().sellBooks(bookList.get(0), 30);
        });
    }

    @Test
    public void test_LibrarianResupplyBooks() {
        librarian.getAccessLevel().setResupplyStockBehaviour(new PermissionToResupply());
        assertThrows(IllegalStateException.class, () -> {
            librarian.getAccessLevel().resupplyStock(bookList.get(0), 30);
        });
    }

    @Test
    public void test_SellingMoreBooksThanInStock() {
        assertThrows(IllegalArgumentException.class, () -> {
            librarian.getAccessLevel().sellBooks(bookList.get(0), 130);
        });
    }

    @Test
    public void test_ManagerResupplyBooks() {
        manager.getAccessLevel().resupplyStock(bookList.get(0), 30);
        assertEquals(130, bookList.get(0).getNrBookInStock());
    }

    @Test
    public void test_ManagerAddNewBook(){
        Book newBook = manager.getAccessLevel().addNewBook("The Lord of the Rings", "9780545010221", "J.R.R. Tolkien", "Fantasy", "Mariner Books", 29.99, false);
        bookList.add(newBook);
        assertEquals(11, bookList.size());
    }

    @Test
    public void test_ManagerNoPermissionToAddNewBook() {
        manager.getAccessLevel().setAddNewBooksBehaviour(new NoPermissionToAddNewBooks());
        assertThrows(IllegalStateException.class, () -> {
            manager.getAccessLevel().addNewBook("The Lord of the Rings", "9780545010221", "J.R.R. Tolkien", "Fantasy", "Mariner Books", 29.99, false);
        });
    }

    @Test
    public void test_addNewBookWithInvalidISBN() {
        assertThrows(IllegalArgumentException.class, () -> {
            manager.getAccessLevel().addNewBook("The Lord of the Rings", "Hello Friend", "J.R.R. Tolkien", "Fantasy", "Mariner Books", 29.99, false);
        });
    }

    @Test
    public void test_addNewBookWithInvalidPrice(){
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

    @Test
    public void test_adminEditsWorker() throws PermissionDeniedException {
        admin.getAccessLevel().editWorker(librarian, "New Name", "New Email",
                                                "New Phone", 1000, new Manager());
        assertAll("Check if the worker was edited",
                () -> assertEquals("New Name", librarian.getFullname()),
                () -> assertEquals("New Email", librarian.getEmail()),
                () -> assertEquals("New Phone", librarian.getPhone()),
                () -> assertEquals(1000, librarian.getSalary()),
                () -> assertTrue(librarian.getAccessLevel() instanceof  Manager)
        );
    }

    @Test
    public void test_managerEditsWorker() {
        assertThrows(PermissionDeniedException.class, () -> {
            manager.getAccessLevel().editWorker(librarian, "New Name", librarian.getEmail(),
                    librarian.getPhone(), librarian.getSalary(), new Manager());
        });
    }

    @Test
    public void test_librarianPromotedToManagerResupplyBooks() throws PermissionDeniedException {
        admin.getAccessLevel().editWorker(librarian, librarian.getFullname(), librarian.getEmail(),
                        librarian.getPhone(), librarian.getSalary(), new Manager());
        librarian.getAccessLevel().resupplyStock(bookList.get(0), 30);
        assertEquals(130, bookList.get(0).getNrBookInStock() );
    }

    @Test
    public void test_adminEditsAnotherAdminWorker() {
        assertThrows(IllegalArgumentException.class, () -> {
            admin.getAccessLevel().editWorker(admin, admin.getFullname(), admin.getEmail(), admin.getPhone(),
                    admin.getSalary(), admin.getAccessLevel());
        });
    }

    @Test
    public void test_adminEditsWorkerWithInvalidSalary() {
        assertThrows(IllegalArgumentException.class, () -> {
            admin.getAccessLevel().editWorker(librarian, "New Name", librarian.getEmail(),
                    librarian.getPhone(), -100, new Manager());
        });
    }

    @Test
    public void test_adminDemotesManagerToLibrarian() throws PermissionDeniedException {
        admin.getAccessLevel().editWorker(manager, manager.getFullname(), manager.getEmail(),
                                        manager.getPhone(), manager.getSalary(), new Librarian());
        assertTrue(manager.getAccessLevel() instanceof Librarian);
    }

    @Test
    public void test_adminDemotesManagerToLibrarianResupplyBooks() throws PermissionDeniedException {
        admin.getAccessLevel().editWorker(manager, manager.getFullname(), manager.getEmail(), manager.getPhone()
                                        , manager.getSalary(), new Librarian());
        assertThrows(IllegalStateException.class, () -> {
            manager.getAccessLevel().resupplyStock(bookList.get(0), 30);
        });
    }

    @Test
    public void test_get100PercentLineCoverageInWorkerClass(){
        Worker worker = new Worker();
        worker.setFullname("New Name");
        worker.setEmail("New Email");
        worker.setPhone("New Phone");
        worker.setPassword("New Password");
        worker.setSalary(1000);
        worker.setDateOfBirth(new Date());
        worker.setAccessLevel(new Manager());
        assertAll("Check if the worker was edited",
                () -> assertEquals("New Name", worker.getFullname()),
                () -> assertEquals("New Email", worker.getEmail()),
                () -> assertEquals("New Password", worker.getPassword()),
                () -> assertEquals("New Phone", worker.getPhone()),
                () -> assertEquals(1000, worker.getSalary()),
                () -> assertTrue(worker.getAccessLevel() instanceof  Manager),
                () -> assertNotNull(worker.getDateOfBirth()),
                () -> assertNotNull(worker.toString())
        );
    }
}
