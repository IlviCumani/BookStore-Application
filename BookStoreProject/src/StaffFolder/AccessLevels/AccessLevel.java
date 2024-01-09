package StaffFolder.AccessLevels;

import BookstoreData.*;
import StaffFolder.AccessLevels.Behaviours.CheckWorkers.CheckWorkerBehaviour;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.Worker;
import StaffFolder.AccessLevels.Behaviours.SellBooks.*;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.*;
import StaffFolder.AccessLevels.Behaviours.FireWorker.FireWorkerBehaviour;
import StaffFolder.AccessLevels.Behaviours.EditWorker.EditWorkerBehaviour;
import StaffFolder.AccessLevels.Behaviours.AddNewWorker.AddNewWorkerBehaviour;

import java.io.Serializable;
import java.util.ArrayList;

public interface AccessLevel extends Serializable {
    public String getAccessLevel();
    public void sellBooks(Book book) throws PermissionDeniedException;
    public void sellBooks(Book book , int amount) throws PermissionDeniedException;
    public void resupplyStock(Book book, int amount);
    public  void resupplyStock(Book book);
    public Book addNewBook(String title, String ISBN13, String author, String genre, String publisher, double price, boolean isPaperback);
    public void setSellBookBehaviour(SellBooksBehaviour sellBooksBehaviour);
    public void setResupplyStockBehaviour(ResupplyStockBehaviour resupplyStockBehaviour);
    public void setAddNewBooksBehaviour(AddNewBooksBehaviour addNewBooksBehaviour);
    public boolean fireWorker(ArrayList<Worker> listOfWorkers, Worker worker) throws PermissionDeniedException;
    public void addNewWorker(ArrayList<Worker> listOfWorker, Worker worker) throws PermissionDeniedException;
    public void editWorker(Worker worker, String fullName, String email, String phoneNumber, double salary, AccessLevel accessLevel) throws PermissionDeniedException;
    public SellBooksBehaviour getSellBooksBehaviour();
    public ResupplyStockBehaviour getResupplyStockBehaviour();
    public AddNewBooksBehaviour getAddNewBooksBehaviour();
    public FireWorkerBehaviour getFireWorkerBehaviour();
    public AddNewWorkerBehaviour getAddNewWorkerBehaviour();
    public EditWorkerBehaviour getEditWorkerBehaviour();
    public CheckWorkerBehaviour getCheckWorkerBehaviour();
    public void setCheckWorkerBehaviour(CheckWorkerBehaviour checkWorkerBehaviour);
}
