package StaffFolder.AccessLevels.Behaviours.AddNewWorker;

import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.Worker;

import java.util.ArrayList;

public interface AddNewWorkerBehaviour {
    public void addNewWorker(ArrayList<Worker> listOfWorkers, Worker worker) throws PermissionDeniedException;
}
