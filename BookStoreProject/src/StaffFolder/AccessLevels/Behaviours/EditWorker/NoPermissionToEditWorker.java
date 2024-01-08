package StaffFolder.AccessLevels.Behaviours.EditWorker;

import StaffFolder.AccessLevels.AccessLevel;
import StaffFolder.AccessLevels.Behaviours.CheckWorkers.CheckWorkerBehaviour;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.AddNewBooksBehaviour;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.ResupplyStockBehaviour;
import StaffFolder.AccessLevels.Behaviours.SellBooks.SellBooksBehaviour;
import StaffFolder.Worker;

public class NoPermissionToEditWorker implements EditWorkerBehaviour{
    @Override
    public void editWorker(Worker worker, String fullName, String email, String phoneNumber, double salary, AccessLevel accessLevel, SellBooksBehaviour sellBooksBehaviour, ResupplyStockBehaviour resupplyStockBehaviour, AddNewBooksBehaviour addNewBooksBehaviour, CheckWorkerBehaviour checkWorkerBehaviour) throws PermissionDeniedException {
        throw new PermissionDeniedException("You don't have permission to edit worker");
    }
}
