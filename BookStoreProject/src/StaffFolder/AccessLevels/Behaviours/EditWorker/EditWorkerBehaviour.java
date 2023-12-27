package StaffFolder.AccessLevels.Behaviours.EditWorker;

import StaffFolder.AccessLevels.AccessLevel;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.Worker;

import java.io.Serializable;

public interface EditWorkerBehaviour extends Serializable {
    public void editWorker(Worker worker, String fullName, String email, String phoneNumber, double salary, AccessLevel accessLevel)throws PermissionDeniedException;
}
