package StaffFolder.AccessLevels;

import BookstoreData.*;
import StaffFolder.Worker;
import StaffFolder.AccessLevels.Behaviours.SellBooks.*;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.*;

public interface AccessLevel {
    public String getAccessLevel();
    public void sellBooks(Book book);
    public void sellBooks(Book book , int amount);
    public void resupplyStock(Book book, int amount);
    public  void resupplyStock(Book book);
    public Book addNewBook();
//!    public void checkWorker(Worker worker);
    public void setSellBooksBehaviour(SellBooksBehaviour sellBooksBehaviour);
    public void setResupplyStockBehaviour(ResupplyStockBehaviour resupplyStockBehaviour);
    public void setAddNewBooksBehaviour(AddNewBooksBehaviour addNewBooksBehaviour);
//!    public void setCheckWorkersBehaviour(CheckWorkersBehaviour checkWorkersBehaviour);
}
