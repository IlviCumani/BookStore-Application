package StaffFolder.AccessLevels;

import BookstoreData.Book;
import StaffFolder.AccessLevels.Behaviours.AddNewWorker.AddNewWorkerBehaviour;
import StaffFolder.AccessLevels.Behaviours.AddNewWorker.NoPermissionToAddNewWorker;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.AccessLevels.Behaviours.FireWorker.FireWorkerBehaviour;
import StaffFolder.AccessLevels.Behaviours.FireWorker.NoPermissionToFireWorker;
import StaffFolder.Worker;
import StaffFolder.AccessLevels.Behaviours.SellBooks.*;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.*;

import java.util.ArrayList;

public class Librarian implements AccessLevel{
    private SellBooksBehaviour sellBooksBehaviour;
    private final ResupplyStockBehaviour resupplyStockBehaviour = new NoPermissionToResupply();
    private final AddNewBooksBehaviour addNewBooksBehaviour = new NoPermissionToAddNewBooks();
    private final FireWorkerBehaviour FIRE_WORKER_BEHAVIOUR = new NoPermissionToFireWorker();
    private final AddNewWorkerBehaviour ADD_NEW_WORKER_BEHAVIOUR = new NoPermissionToAddNewWorker();
//    private final CheckWorkersBehaviour checkWorkersBehaviour = new NoPermissionToCheckWorkers();

    public Librarian(){
        sellBooksBehaviour = new PermissionToSellBooks();
    }

    public void setSellBookBehaviour(SellBooksBehaviour sellBooksBehaviour) {
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
    public void sellBooks(Book book , int amount) throws PermissionDeniedException {
        sellBooksBehaviour.sellBooks(book, amount );
    }

    @Override
    public void sellBooks(Book book) throws PermissionDeniedException {
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
        return addNewBooksBehaviour.addNewBooks(title, ISBN13, author, genre, publisher, price, isPaperback);
    }

//    @Override
//    public void checkWorker(Worker worker) {
//        checkWorkersBehaviour.checkWorker(worker);
//    }

    @Override
    public String getAccessLevel() {
        return "Librarian";
    }

    @Override
    public boolean fireWorker(ArrayList<Worker> listOfWorkers, Worker worker) throws PermissionDeniedException {
        return FIRE_WORKER_BEHAVIOUR.fireWorker(listOfWorkers, worker);
    }

    @Override
    public void addNewWorker(ArrayList<Worker> listOfWorkers, Worker worker) throws PermissionDeniedException {
        ADD_NEW_WORKER_BEHAVIOUR.addNewWorker(listOfWorkers, worker);
    }
}
