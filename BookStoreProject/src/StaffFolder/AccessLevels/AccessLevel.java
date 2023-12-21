package StaffFolder.AccessLevels;

import BookstoreData.*;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.Worker;
import StaffFolder.AccessLevels.Behaviours.SellBooks.*;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.*;

import java.util.ArrayList;

public interface AccessLevel {
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

}
