import BookstoreData.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class TestAddBookFX extends TestAbstractLogInSetup{
    public final String titleTF = "#titleTF";

    public final String addBookBtn = "#addBookBtn";

    public final String isbnTF = "#isbnTF";


    public final String priceTF = "#priceTF";

    public final String rbEbook = "#rbEbook";

    public final String authorTF = "#authorTF";

    public final String genreTF = "#genreTF";

    public final String publisherTF = "#publisherTF";

    public final String submitBtn = "#submitBtn";

    ArrayList<Book> books = new ArrayList<>();

    @BeforeEach
    void newsetUp() {
        books.add( new Book(
                "The Gulag Archipelago1",
                "978-0-06-125379-4",
                "Aleksandr Solzhenitsyn",
                "Non-Fiction",
                "Harper & Row",
                29.99,
                true
        ));
    }
    @Test
    void test_addNewBook() {
        this.adminLogIn();
        sleep(1000);
        clickOn(addBookBtn);
        sleep(1000);
        clickOn(titleTF);
        sleep(1000);
        write(books.getFirst().getBookTitle());
        sleep(3000);
        clickOn(isbnTF);
        write(books.getFirst().getBookISBN13());
        sleep(1000);
        clickOn(priceTF);
        write(Double.toString(books.getFirst().getBookPrice()));
        sleep(1000);
        clickOn(rbEbook);
        clickOn(authorTF);
        sleep(1000);
        write(books.getFirst().getBookAuthor());
        sleep(1000);
        clickOn(genreTF);
        write(books.getFirst().getBookGenre());
        sleep(1000);
        clickOn(publisherTF);
        sleep(1000);
        write(books.getFirst().getBookPublisher());
        clickOn(submitBtn);
        sleep(1000);
    }

}
