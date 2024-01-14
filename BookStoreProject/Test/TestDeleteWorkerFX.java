import IO.CompatibleTypes;
import IO.FileIO;
import IO.WorkerFileIOService;
import StaffFolder.Worker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestDeleteWorkerFX extends TestAbstractLogInSetup{
    private final String deletWorkerBtn = "#deletWorkerBtn";

    private final String workerTableView = "#workerTableView";
    private final String EmployeeTab = "#EmployeeTab";
    private ArrayList<Worker> workers;
    @BeforeEach
    void theRealSetUp() {
        FileIO fileIO = new FileIO(new WorkerFileIOService());
        this.workers = CompatibleTypes.fromSerializbleToWorker(fileIO.read());

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

    @Test
    void test_deleteWorker() {
        this.actionOnWorker("#Nomi_Mng");
        clickOn(deletWorkerBtn);
        sleep(1000);
        clickOn(EmployeeTab);
        sleep(3000);

    }

    @Test
    void test_editWorker() {

    }
}
