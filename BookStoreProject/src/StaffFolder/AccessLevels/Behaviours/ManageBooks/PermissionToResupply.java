package StaffFolder.AccessLevels.Behaviours.ManageBooks;

import BookstoreData.Book;

public class PermissionToResupply implements ResupplyStockBehaviour{
    @Override
    public void resupplyStock(Book book, int amount) {
        System.out.println("Resupplying " + amount + " of " + book.getBookTitle());
        book.setNrBookInStock(book.getNrBookInStock() + amount);
    }
}
