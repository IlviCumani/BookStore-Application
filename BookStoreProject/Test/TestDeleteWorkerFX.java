import IO.CompatibleTypes;
import IO.FileIO;
import IO.WorkerFileIOService;
import StaffFolder.Worker;
import org.junit.jupiter.api.BeforeEach;

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




}
