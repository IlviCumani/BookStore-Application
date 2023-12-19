package StaffFolder.AccessLevels;

import BookstoreData.Book;
import StaffFolder.Worker;
import StaffFolder.AccessLevels.Behaviours.SellBooks.*;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.*;

public class Administrator implements AccessLevel{

    private final SellBooksBehaviour sellBooksBehaviour = new PermissionToSellBooks();
    private final ResupplyStockBehaviour resupplyStockBehaviour = new PermissionToResupply();
    private final AddNewBooksBehaviour addNewBooksBehaviour = new PermissionToAddNewBook();
//    private final CheckWorkersBehaviour checkWorkersBehaviour = new AdminCheckWorkers();

    @Override
    public void sellBooks(Book book , int amount){
        sellBooksBehaviour.sellBooks(book , amount);
    }

    @Override
    public void sellBooks(Book book){
        sellBooksBehaviour.sellBooks(book , 1);
    }

    @Override
    public void resupplyStock(Book book, int amount) {
        resupplyStockBehaviour.resupplyStock(book, amount);
    }

    @Override
    public void resupplyStock(Book book) {
        resupplyStockBehaviour.resupplyStock(book, 50);
    }

    @Override
    public Book addNewBook(String title, String ISBN13, String author, String genre, String publisher, double price, boolean isPaperback){
        return  addNewBooksBehaviour.addNewBooks(title, ISBN13, author, genre, publisher, price, isPaperback);
    }

//    @Override
//    public void checkWorker(Worker worker) {
//        checkWorkersBehaviour.checkWorker(worker);
//    }

    @Override
    public void setSellBooksBehaviour(SellBooksBehaviour sellBooksBehaviour) {
        System.out.println("Administrator can't change sell books behaviour");
    }

    @Override
    public void setResupplyStockBehaviour(ResupplyStockBehaviour resupplyStockBehaviour) {
        System.out.println("Administrator can't change resupply stock behaviour");
    }

    @Override
    public void setAddNewBooksBehaviour(AddNewBooksBehaviour addNewBooksBehaviour) {
        System.out.println("Administrator can't change add new books behaviour");
    }

//    @Override
//    public void setCheckWorkersBehaviour(CheckWorkersBehaviour checkWorkersBehaviour) {
//        System.out.println("Administrator can't change check workers behaviour");
//    }

    @Override
    public String getAccessLevel() {
        return "Administrator";
    }
}
