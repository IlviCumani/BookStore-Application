import BookstoreData.Book;
import IO.BookFileIOService;
import IO.CompatibleTypes;
import IO.FileIO;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;


public class TestResuplyBooksFX extends TestAbstractLogInSetup {
    public final String bookTableView = "#bookTableView";

    public final String nrBooks = "#nrBooks";



    public final String addBookToStockBtn = "#addBookToStockBtn";

    public final String purchaseBookBtn = "#purchaseBookBtn";

    private final FileIO fileio = new FileIO(new BookFileIOService());

    private ArrayList<Serializable> serialibleBookList = new ArrayList<>();

    @BeforeEach
    void bookArraySerialSetUp() {
        this.serialibleBookList = fileio.read();
    }

    void logInAndClickBook(String nr){
        this.adminLogIn();
//        NodeQueryImpl<FxRobot> tableViewQuery = new FxRobot().lookup("#yourTableViewId");
        clickOn(bookTableView);
        doubleClickOn();
        sleep(1000);
        clickOn(nrBooks);
        sleep(1000);
        write(nr);

        sleep(1000);
        press(KeyCode.ENTER);
        sleep(1000);
    }


    @Test
    void test_firstTest() {
        this.logInAndClickBook("20");
        clickOn(addBookToStockBtn);
        sleep(1000);
    }

    @Test
    void test_SellBooks() {
        this.logInAndClickBook("20");
        sleep(1000);
        clickOn(purchaseBookBtn);
    }

    @Test
    void test_SellBooksToTriggerAlert(){
        int nr = this.findTheAmountBook();
        this.logInAndClickBook(Integer.toString(nr));
        sleep(1000);
        clickOn(purchaseBookBtn);
        sleep(3000);
    }

    private int findTheAmountBook() {
        String n1 = "9780143105428";

        Book wantedBook = null;
        for(Book book: CompatibleTypes.fromSerializbleToBook(serialibleBookList)){
            if(book.getBookISBN13().equals(n1))
                wantedBook = book;
        }
        if (wantedBook != null && wantedBook.getNrBookInStock() >= 5)
            return  wantedBook.getNrBookInStock() - 4;
        return 0;



    }

}
