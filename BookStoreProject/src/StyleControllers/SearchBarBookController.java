package StyleControllers;

import BookstoreData.Book;

import java.util.ArrayList;

public class SearchBarBookController {

    public static Book findBook(String title, ArrayList<Book> listOfBooks) {
        for(Book book: listOfBooks) {
            if (book.getBookTitle().equals(title))
                return book;
        }
        return null;
    }
}
