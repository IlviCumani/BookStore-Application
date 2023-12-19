package StaffFolder.AccessLevels.Behaviours.SellBooks;
import BookstoreData.Book;
public class NoPermissionToSellBooks implements SellBooksBehaviour{
    @Override
    public void sellBooks(Book book , int amount) {
        throw new IllegalArgumentException("You don't have permission to sell books");
    }
}
