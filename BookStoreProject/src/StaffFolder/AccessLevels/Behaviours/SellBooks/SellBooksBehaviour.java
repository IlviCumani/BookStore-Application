package StaffFolder.AccessLevels.Behaviours.SellBooks;
import BookstoreData.Book;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;

import java.io.Serializable;

public interface SellBooksBehaviour extends Serializable {
    public void sellBooks(Book book , int amount) throws PermissionDeniedException;
}
