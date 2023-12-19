package StaffFolder.AccessLevels.Behaviours.ManageBooks;

import BookstoreData.Book;

public class NoPermissionToAddNewBooks implements AddNewBooksBehaviour{
    @Override
    public Book addNewBooks(String title, String ISBN13, String author, String genre, String publisher, double price, boolean isPaperback){
        throw new IllegalStateException("You don't have permission to add new books");
    }
}
