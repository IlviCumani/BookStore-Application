package StaffFolder.AccessLevels.Behaviours.CheckWorkers;

public class NoPermissionToCheckWorker implements CheckWorkerBehaviour{
    @Override
    public boolean checkWorker() {
        return false;
    }
}
