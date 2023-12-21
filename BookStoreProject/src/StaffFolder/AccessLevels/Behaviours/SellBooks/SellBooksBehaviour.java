package StaffFolder.AccessLevels.Behaviours.SellBooks;
import BookstoreData.Book;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;

public interface SellBooksBehaviour {
    public void sellBooks(Book book , int amount) throws PermissionDeniedException;
}
