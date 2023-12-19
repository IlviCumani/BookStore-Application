package StaffFolder.AccessLevels.Behaviours.ManageBooks;
import BookstoreData.Book;
public interface AddNewBooksBehaviour {
    public Book addNewBooks(String title, String ISBN13, String author, String genre, String publisher, double price, boolean isPaperback);
}
