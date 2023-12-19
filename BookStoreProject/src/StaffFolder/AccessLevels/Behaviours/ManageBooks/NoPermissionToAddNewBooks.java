package StaffFolder.AccessLevels.Behaviours.ManageBooks;

import BookstoreData.Book;

public class NoPermissionToAddNewBooks implements AddNewBooksBehaviour{
    @Override
    public Book addNewBooks(){
        throw new IllegalArgumentException("You don't have permission to add new books");
    }
}
