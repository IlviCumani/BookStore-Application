package StaffFolder.AccessLevels;

import BookstoreData.Book;
import StaffFolder.Worker;
import StaffFolder.AccessLevels.Behaviours.SellBooks.*;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.*;

public class Librarian implements AccessLevel{
    private SellBooksBehaviour sellBooksBehaviour;
    private final ResupplyStockBehaviour resupplyStockBehaviour = new NoPermissionToResupply();
    private final AddNewBooksBehaviour addNewBooksBehaviour = new NoPermissionToAddNewBooks();
//    private final CheckWorkersBehaviour checkWorkersBehaviour = new NoPermissionToCheckWorkers();


    public Librarian(){
        sellBooksBehaviour = new PermissionToSellBooks();
    }

    public void setSellBooksBehaviour(SellBooksBehaviour sellBooksBehaviour) {
        this.sellBooksBehaviour = sellBooksBehaviour;
    }

    @Override
    public void setResupplyStockBehaviour(ResupplyStockBehaviour resupplyStockBehaviour) {
        System.out.println("Librarian can't change resupply stock behaviour");
    }

    @Override
    public void setAddNewBooksBehaviour(AddNewBooksBehaviour addNewBooksBehaviour) {
        System.out.println("Librarian can't change add new books behaviour");
    }

//    @Override
//    public void setCheckWorkersBehaviour(CheckWorkersBehaviour checkWorkersBehaviour) {
//        System.out.println("Librarian can't change check workers behaviour");
//    }

    @Override
    public void sellBooks(Book book , int amount){
        sellBooksBehaviour.sellBooks(book, amount );
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
    public Book addNewBook(){
        return addNewBooksBehaviour.addNewBooks();
    }

//    @Override
//    public void checkWorker(Worker worker) {
//        checkWorkersBehaviour.checkWorker(worker);
//    }

    @Override
    public String getAccessLevel() {
        return "Librarian";
    }
}
