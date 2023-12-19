package StaffFolder.AccessLevels.Behaviours.AddNewWorker;

import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.Worker;

import java.util.ArrayList;

public class NoPermissionToAddNewWorker implements AddNewWorkerBehaviour{

    @Override
    public void addNewWorker(ArrayList<Worker> listOfWorkers, Worker worker) throws PermissionDeniedException {
        throw new PermissionDeniedException("JOOOO MER!!");
    }
}
