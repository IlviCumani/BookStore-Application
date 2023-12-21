package StaffFolder.AccessLevels.Behaviours.SellBooks;
import BookstoreData.Book;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;

public class NoPermissionToSellBooks implements SellBooksBehaviour{
    @Override
    public void sellBooks(Book book , int amount) throws PermissionDeniedException {
        throw new PermissionDeniedException("You don't have permission to sell books");
    }
}
