package StaffFolder.AccessLevels.Behaviours.AddNewWorker;

import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.Worker;

import java.io.Serializable;
import java.util.ArrayList;

public interface AddNewWorkerBehaviour extends Serializable {
    public void addNewWorker(ArrayList<Worker> listOfWorkers, Worker worker) throws PermissionDeniedException;
}
