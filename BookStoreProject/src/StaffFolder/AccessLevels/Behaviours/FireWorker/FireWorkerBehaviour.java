package StaffFolder.AccessLevels.Behaviours.FireWorker;

import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.Worker;

import java.util.ArrayList;

public interface FireWorkerBehaviour {

    boolean fireWorker(ArrayList<Worker> listOfWorkers, Worker worker) throws PermissionDeniedException;
}
