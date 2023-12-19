package StaffFolder.AccessLevels.Behaviours.SellBooks;

import BookstoreData.Book;

public class PermissionToSellBooks implements SellBooksBehaviour{
    @Override
    public void sellBooks(Book book, int amount) {
        if(amount > book.getNrBookInStock())
            throw new IllegalArgumentException("Not enough books in stock");

        book.setNrBookInStock(book.getNrBookInStock() - amount);
    }
}
