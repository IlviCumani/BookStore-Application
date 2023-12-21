package StaffFolder.AccessLevels.Behaviours.EditWorker;

import StaffFolder.AccessLevels.AccessLevel;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.Worker;

public interface EditWorkerBehaviour {
    public void editWorker(Worker worker, String fullName, String email, String phoneNumber, double salary, AccessLevel accessLevel)throws PermissionDeniedException;
}
