package StaffFolder.AccessLevels.Behaviours.SellBooks;

import BookstoreData.Book;

public class PermissionToSellBooks implements SellBooksBehaviour{
    @Override
    public void sellBooks(Book book, int amount) {
        book.setNrBookInStock(book.getNrBookInStock() - amount);
    }
}
