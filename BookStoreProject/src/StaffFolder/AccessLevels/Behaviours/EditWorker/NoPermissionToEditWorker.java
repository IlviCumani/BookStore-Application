package StaffFolder.AccessLevels.Behaviours.EditWorker;

import StaffFolder.AccessLevels.AccessLevel;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.Worker;

public class NoPermissionToEditWorker implements EditWorkerBehaviour{
    @Override
    public void editWorker(Worker worker, String fullName, String email, String phoneNumber, double salary, AccessLevel accessLevel) throws PermissionDeniedException {
        throw new PermissionDeniedException("You don't have permission to edit worker");
    }
}
