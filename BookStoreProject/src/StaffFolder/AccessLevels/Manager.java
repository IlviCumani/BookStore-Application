package StaffFolder.AccessLevels;

import StaffFolder.Worker;
import BookstoreData.Book;
import StaffFolder.AccessLevels.Behaviours.SellBooks.*;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.*;

public class Manager implements AccessLevel{
    private SellBooksBehaviour sellBooksBehaviour;
    private ResupplyStockBehaviour resupplyStockBehaviour;
    private AddNewBooksBehaviour addNewBooksBehaviour;
//    private CheckWorkersBehaviour checkWorkersBehaviour;

    public Manager(){
        this.sellBooksBehaviour = new PermissionToSellBooks();
        this.resupplyStockBehaviour = new PermissionToResupply();
        this.addNewBooksBehaviour = new PermissionToAddNewBook();
//        this.checkWorkersBehaviour = new ManagerCheckWorkers();
    }

    public void setSellBooksBehaviour(SellBooksBehaviour sellBooksBehaviour) {
        this.sellBooksBehaviour = sellBooksBehaviour;
    }

    public void setResupplyStockBehaviour(ResupplyStockBehaviour resupplyStockBehaviour) {
        this.resupplyStockBehaviour = resupplyStockBehaviour;
    }

    public void setAddNewBooksBehaviour(AddNewBooksBehaviour addNewBooksBehaviour) {
        this.addNewBooksBehaviour = addNewBooksBehaviour;
    }

//    public void setCheckWorkersBehaviour(CheckWorkersBehaviour checkWorkersBehaviour)throws IllegalArgumentException{
//        if(checkWorkersBehaviour instanceof AdminCheckWorkers){
//            throw new IllegalArgumentException("Manager can't check Admins");
//        }
//        this.checkWorkersBehaviour = checkWorkersBehaviour;
//    }

    @Override
    public String getAccessLevel() {
        return "Manager";
    }

    @Override
    public void sellBooks(Book book, int amount){
        sellBooksBehaviour.sellBooks(book, amount);
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
//        if(worker.getAccessLevel() instanceof Librarian){
//            checkWorkersBehaviour.checkWorker(worker);
//        }
//        else{
//            throw new IllegalArgumentException("Manager can't check Admins or other managers");
//        }
//    }
}
