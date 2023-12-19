package StaffFolder.AccessLevels.Behaviours.AddNewWorker;

import StaffFolder.Worker;

import java.util.ArrayList;

public class PermissionToAddNewWorker implements AddNewWorkerBehaviour{

    @Override
    public void addNewWorker(ArrayList<Worker> listOfWorkers, Worker worker) {
        listOfWorkers.add(worker);
    }
}
