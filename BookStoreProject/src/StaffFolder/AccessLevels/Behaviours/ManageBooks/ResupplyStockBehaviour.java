package StaffFolder.AccessLevels.Behaviours.ManageBooks;

import BookstoreData.Book;

public interface ResupplyStockBehaviour {
    public void resupplyStock(Book book, int amount);
}
