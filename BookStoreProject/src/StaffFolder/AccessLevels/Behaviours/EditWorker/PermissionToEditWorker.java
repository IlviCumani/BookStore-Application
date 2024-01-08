package StaffFolder.AccessLevels.Behaviours.EditWorker;
import StaffFolder.AccessLevels.AccessLevel;
import StaffFolder.AccessLevels.Administrator;
import StaffFolder.AccessLevels.Behaviours.CheckWorkers.CheckWorkerBehaviour;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.AddNewBooksBehaviour;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.ResupplyStockBehaviour;
import StaffFolder.AccessLevels.Behaviours.SellBooks.SellBooksBehaviour;
import StaffFolder.Worker;
public class PermissionToEditWorker implements EditWorkerBehaviour{

    @Override
    public void editWorker(Worker worker, String fullName, String email, String phoneNumber, double salary, AccessLevel accessLevel, SellBooksBehaviour sellBooksBehaviour, ResupplyStockBehaviour resupplyStockBehaviour, AddNewBooksBehaviour addNewBooksBehaviour, CheckWorkerBehaviour checkWorkerBehaviour) throws PermissionDeniedException {
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
        worker.getAccessLevel().setSellBookBehaviour(sellBooksBehaviour);
        worker.getAccessLevel().setResupplyStockBehaviour(resupplyStockBehaviour);
        worker.getAccessLevel().setAddNewBooksBehaviour(addNewBooksBehaviour);
        worker.getAccessLevel().setCheckWorkerBehaviour(checkWorkerBehaviour);
    }
}
