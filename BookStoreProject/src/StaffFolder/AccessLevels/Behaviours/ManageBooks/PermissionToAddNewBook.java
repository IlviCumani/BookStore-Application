package StaffFolder.AccessLevels.Behaviours.ManageBooks;
import BookstoreData.Book;
public class PermissionToAddNewBook implements AddNewBooksBehaviour {
    @Override
    public Book addNewBooks(String title, String ISBN13, String author, String genre, String publisher, double price, boolean isPaperback) {
        if(price < 0){
            throw new IllegalArgumentException("Price can't be negative");
        }
        String isbn13Regex = "^(?=(?:\\D*\\d){13}\\D*$)[\\d-]+$";
        if (ISBN13.matches(isbn13Regex)) {
            return new Book(title, ISBN13, author, genre, publisher, price, isPaperback);
        } else {
            throw new IllegalArgumentException("ISBN13 is not valid");
        }
    }
}
