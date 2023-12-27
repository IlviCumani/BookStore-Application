package StaffFolder.AccessLevels.Behaviours.ManageBooks;

import BookstoreData.Book;

import java.io.Serializable;

public interface ResupplyStockBehaviour extends Serializable {
    public void resupplyStock(Book book, int amount);
}
