package StaffFolder.AccessLevels.Behaviours.ManageBooks;

import BookstoreData.Book;

public class NoPermissionToResupply implements ResupplyStockBehaviour{
    @Override
    public void resupplyStock(Book book, int amount) {
        throw new IllegalStateException("You don't have permission to resupply stock");
    }
}
