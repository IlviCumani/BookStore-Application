package StaffFolder.AccessLevels.Behaviours.FireWorker;

import StaffFolder.AccessLevels.Administrator;
import StaffFolder.Worker;

import java.util.ArrayList;

public class PermissionToFireWorker implements  FireWorkerBehaviour{

    @Override
    public boolean fireWorker(ArrayList<Worker> listOfWorkers, Worker worker){
        if (worker.getAccessLevel() instanceof Administrator)
            throw new IllegalArgumentException("You cannot delete another Administrator");
        return listOfWorkers.remove(worker);
    }
}
