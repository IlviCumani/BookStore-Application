package StaffFolder.AccessLevels.Behaviours.ManageBooks;
import BookstoreData.Book;

import java.io.Serializable;

public interface AddNewBooksBehaviour extends Serializable {
    public Book addNewBooks(String title, String ISBN13, String author, String genre, String publisher, double price, boolean isPaperback);
}
