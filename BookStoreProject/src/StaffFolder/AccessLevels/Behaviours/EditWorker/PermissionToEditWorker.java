package StaffFolder.AccessLevels.Behaviours.EditWorker;
import StaffFolder.AccessLevels.AccessLevel;
import StaffFolder.AccessLevels.Administrator;
import StaffFolder.Worker;
public class PermissionToEditWorker implements EditWorkerBehaviour{

    @Override
    public void editWorker(Worker worker, String fullName, String email, String phoneNumber, double salary, AccessLevel accessLevel) {
        if(worker.getAccessLevel() instanceof Administrator){
            throw new IllegalArgumentException("You can't edit administrator");
        }
        if(salary < 0){
            throw new IllegalArgumentException("Salary can't be negative");
        }
        worker.setFullname(fullName);
        worker.setEmail(email);
        worker.setPhone(phoneNumber);

        worker.setSalary(salary);
        worker.setAccessLevel(accessLevel);
    }
}
