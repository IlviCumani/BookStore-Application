package StaffFolder.AccessLevels;

import StaffFolder.AccessLevels.Behaviours.AddNewWorker.AddNewWorkerBehaviour;
import StaffFolder.AccessLevels.Behaviours.AddNewWorker.NoPermissionToAddNewWorker;
import StaffFolder.AccessLevels.Behaviours.EditWorker.EditWorkerBehaviour;
import StaffFolder.AccessLevels.Behaviours.EditWorker.NoPermissionToEditWorker;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.AccessLevels.Behaviours.FireWorker.FireWorkerBehaviour;
import StaffFolder.AccessLevels.Behaviours.FireWorker.NoPermissionToFireWorker;
import StaffFolder.Worker;
import BookstoreData.Book;
import StaffFolder.AccessLevels.Behaviours.SellBooks.*;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.*;

import java.util.ArrayList;

public class Manager implements AccessLevel{
    private SellBooksBehaviour sellBooksBehaviour;
    private ResupplyStockBehaviour resupplyStockBehaviour;
    private AddNewBooksBehaviour addNewBooksBehaviour;
    private final FireWorkerBehaviour FIRE_WORKER_BEHAVIOUR = new NoPermissionToFireWorker();
    private final AddNewWorkerBehaviour ADD_NEW_WORKER_BEHAVIOUR = new NoPermissionToAddNewWorker();
    private final EditWorkerBehaviour EDIT_WORKER_BEHAVIOUR = new NoPermissionToEditWorker();

    public Manager(){
        this.sellBooksBehaviour = new PermissionToSellBooks();
        this.resupplyStockBehaviour = new PermissionToResupply();
        this.addNewBooksBehaviour = new PermissionToAddNewBook();
    }

    @Override
    public void setSellBookBehaviour(SellBooksBehaviour sellBooksBehaviour) {
        this.sellBooksBehaviour = sellBooksBehaviour;
    }

    public void setResupplyStockBehaviour(ResupplyStockBehaviour resupplyStockBehaviour) {
        this.resupplyStockBehaviour = resupplyStockBehaviour;
    }

    public void setAddNewBooksBehaviour(AddNewBooksBehaviour addNewBooksBehaviour) {
        this.addNewBooksBehaviour = addNewBooksBehaviour;
    }



    @Override
    public String getAccessLevel() {
        return "Manager";
    }

    @Override
    public void sellBooks(Book book, int amount) throws PermissionDeniedException {
        sellBooksBehaviour.sellBooks(book, amount);
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

    @Override
    public boolean fireWorker(ArrayList<Worker> listOfWorkers, Worker worker)throws PermissionDeniedException {
        return FIRE_WORKER_BEHAVIOUR.fireWorker(listOfWorkers, worker);
    }

    @Override
    public void addNewWorker(ArrayList<Worker> listOfWorkers, Worker worker) throws PermissionDeniedException {
        ADD_NEW_WORKER_BEHAVIOUR.addNewWorker(listOfWorkers, worker);
    }

    @Override
    public void editWorker(Worker worker, String fullName, String email, String phoneNumber, double salary, AccessLevel accessLevel) throws PermissionDeniedException {
        EDIT_WORKER_BEHAVIOUR.editWorker(worker, fullName, email, phoneNumber, salary, accessLevel);
    }
}
