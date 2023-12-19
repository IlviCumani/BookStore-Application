package StaffFolder.AccessLevels.Behaviours.FireWorker;

import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.Worker;

import java.util.ArrayList;

public class NoPermissionToFireWorker implements FireWorkerBehaviour{

    @Override
    public boolean fireWorker(ArrayList<Worker> listOfWorkers, Worker worker) throws PermissionDeniedException {
        throw new PermissionDeniedException("YOU HAVE NO PERMISSION JEVG");
    }
}
