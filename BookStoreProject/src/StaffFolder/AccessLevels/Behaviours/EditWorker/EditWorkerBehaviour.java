package StaffFolder.AccessLevels.Behaviours.EditWorker;

import StaffFolder.AccessLevels.AccessLevel;
import StaffFolder.AccessLevels.Behaviours.CheckWorkers.CheckWorkerBehaviour;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.AddNewBooksBehaviour;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.ResupplyStockBehaviour;
import StaffFolder.AccessLevels.Behaviours.SellBooks.SellBooksBehaviour;
import StaffFolder.Worker;

import java.io.Serializable;

public interface EditWorkerBehaviour extends Serializable {
    public void editWorker(Worker worker, String fullName, String email, String phoneNumber, double salary, AccessLevel accessLevel)throws PermissionDeniedException;
}
