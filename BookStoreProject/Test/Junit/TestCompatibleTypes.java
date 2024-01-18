package Junit;

import BookstoreData.Book;
import IO.CompatibleTypes;
import StaffFolder.AccessLevels.Administrator;
import StaffFolder.AccessLevels.Librarian;
import StaffFolder.AccessLevels.Manager;
import StaffFolder.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestCompatibleTypes {
    private ArrayList<Book> bookList;

    private ArrayList<Worker> listOfWorkers;

    private ArrayList<Serializable> bookListSerializible;

    private ArrayList<Serializable> listOfWorkersSerializible;



    @BeforeEach
    void setup() {
        Worker librarian = new Worker("Lib", "librarian", "12345678", "0675850510", null, new Librarian(), 500);
        Worker manager = new Worker("manager", "manager", "12345678", "0675850510", null, new Manager(), 700);
        Worker admin = new Worker("admin", "admin", "12345678", "0675850510", null, new Administrator(), 1000);
        Book book1 = new Book("The Catcher in the Rye", "9780316769488", "J.D. Salinger", "Fiction", "Little, Brown and Company", 15.99, true);
        Book book2 = new Book("To Kill a Mockingbird", "9780061120084", "Harper Lee", "Classics", "Harper Perennial Modern Classics", 12.50, true);
        Book book3 = new Book("1984", "9780451524935", "George Orwell", "Science Fiction", "Signet Classic", 9.99, true);

        this.bookList = new ArrayList<>();
        this.bookListSerializible = new ArrayList<>();
        this.listOfWorkersSerializible = new ArrayList<>();
        this.listOfWorkers = new ArrayList<>();
        Worker[] workers = {librarian, manager, admin};
        Book[] books = {book1, book2, book3};
        Serializable[] bookSerializble = {book1, book2, book3};
        Serializable[] workersSerializible = {librarian, manager, admin};
        listOfWorkers.addAll(List.of(workers));
        this.listOfWorkersSerializible.addAll(List.of(workersSerializible));
        bookList.addAll(List.of(books));
        bookListSerializible.addAll(List.of(bookSerializble));
    }

    @Test
    void test_seraliziblesToWorker() {
        Assertions.assertEquals(this.listOfWorkers, CompatibleTypes.fromSerializbleToWorker(listOfWorkersSerializible));
    }

    @Test
    void test_serializibleToWorkerWithNullValues() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            CompatibleTypes.fromWorkerToSerializible(null);
        });
    }

    @Test
    void test_serializiblesToWorkerWithUnwantedType() {
        Assertions.assertEquals(new ArrayList<>(), CompatibleTypes.fromSerializbleToWorker(bookListSerializible));

    }

    @Test
    void test_seraliziblesToBook() {
        Assertions.assertEquals(this.bookList, CompatibleTypes.fromSerializbleToBook(bookListSerializible));
    }

    @Test
    void test_serializibleToBookWithNullValues() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            CompatibleTypes.fromSerializbleToBook(null);
        });
    }

    @Test
    void test_serializiblesToBookWithUnwantedType() {
        Assertions.assertEquals(new ArrayList<>(), CompatibleTypes.fromSerializbleToBook(listOfWorkersSerializible));

    }
}
