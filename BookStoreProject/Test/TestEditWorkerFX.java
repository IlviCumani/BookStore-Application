import IO.CompatibleTypes;
import IO.FileIO;
import IO.WorkerFileIOService;
import StaffFolder.Worker;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestEditWorkerFX extends TestAbstractLogInSetup{
    private final String editWorkerBtn = "#editWorkerBtn";

    private final String newAccessLevelString = "#newAccessLevelString";

    private final String workerTableView = "#workerTableView";

    private final String EmployeeTab = "#EmployeeTab";

    private final String newEmail = "#newEmail";

    private final String workerEmail = "#workerEmail";

    private final String newSalary = "#newSalary";

    private final String workerSalary = "#workerSalary";

    private final String newPhoneNumber = "#newPhoneNumber";

    private final String workerPhoneNumber = "#workerPhoneNumber";

    private final String[] accessList = {"Librarian", "Manager", "Administrator" };

    private ArrayList<Worker> workers;

    @BeforeEach
    void theWorkerSetUp() {
        FileIO fileIO = new FileIO(new WorkerFileIOService());
        ArrayList<Worker> workerss = CompatibleTypes.fromSerializbleToWorker(fileIO.read());
        this.workers = this.filterWorkerForNoAdmin(workerss);

    }

    private ArrayList<Worker> filterWorkerForNoAdmin(ArrayList<Worker> listOfWorkers) {
//        ArrayList<Worker> workers1 =
        for(Worker worker: listOfWorkers) {
            if (worker.getAccessLevelName().equals("Admin")){
                listOfWorkers.remove(worker);
            }
        }
        return listOfWorkers;
    }

    void actionOnWorker(String email){
        this.adminLogIn();


        clickOn(EmployeeTab);
//        System.out.println(workerTableView);
        sleep(1000);
//        doubleClickOn((Node) lookup("table-row-cell").nth(1).query());
        clickOn(workerTableView);
        doubleClickOn(email);
        sleep(1000);
    }

    Worker findWorker(String name) {
        for(Worker worker: workers) {
            if (worker.getEmail().equals(name))
                return worker;
        }
        return null;
    }

    @Test
    void test_changeWorker() {
        String email = "aasasd";
        System.out.println(email);
        this.actionOnWorker("#" + email);
        sleep(3000);
        clickOn(workerEmail);
        clickOn(newEmail);
        write("aasasd");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        clickOn(newAccessLevelString);
        clickOn(accessList[1]);
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        sleep(1000);
        clickOn(workerSalary);
        sleep(1000);
        clickOn(newSalary);
        write(Double.toString(findWorker(email).getSalary()) + "0");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        sleep(1000);
        clickOn(workerPhoneNumber);
        clickOn(newPhoneNumber);
        sleep(1000);
        write("3556991");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        sleep(1000);
        sleep(1000);

        clickOn(editWorkerBtn);

//       write(findWorker(email).getEmail() + "a");
    }

    @AfterEach
    void clickOnEmployeeTab() {
        clickOn(EmployeeTab);
        sleep(1000);
    }

}
