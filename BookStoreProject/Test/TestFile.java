import IO.FileIO;
import IO.FileIOServiceInjectable;
import IO.MockFileIOService;
import IO.WorkerFileIOService;
import StaffFolder.AccessLevels.Administrator;
import StaffFolder.AccessLevels.Librarian;
import StaffFolder.AccessLevels.Manager;
import StaffFolder.Worker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestFile {

    private FileIOServiceInjectable fileIoService;
    private ArrayList<Serializable> listOfWorkers;

    @BeforeEach
    void setUp() {
        Worker librarian = new Worker("Lib_Bosi", "Librarian", "1", "0675850510", null, new Librarian(), 500);
        Worker manager = new Worker("manager", "Manager", "1", "0675850510", null, new Manager(), 700);
        Worker admin = new Worker("admin", "Admin", "1", "0675850510", null, new Administrator(), 1000);
        listOfWorkers = new ArrayList<>();
        Worker[] workers = {librarian, manager, admin};
        listOfWorkers.addAll(List.of(workers));
        fileIoService = new MockFileIOService();
    }

    @Test
    void test_verifyTheFileWrite() {
        FileIO fileIO = new FileIO(fileIoService);
        fileIO.write(listOfWorkers);
        assertEquals(listOfWorkers, fileIO.read());
    }

    @Test
    void test_writeWithoutDuplicates() {
        FileIO fileIO = new FileIO(fileIoService);
        fileIO.write(listOfWorkers);
        Worker dummy_Worker = new Worker();
        listOfWorkers.add(dummy_Worker);
        fileIO.write(listOfWorkers);
        assertEquals(listOfWorkers, fileIO.read());
    }

    @Test
    void test_getFileIOService() {
        FileIO fileIO = new FileIO(fileIoService);
        assertEquals(fileIoService, fileIO.getFileService());
    }

    @Test
    void test_setFileIOService() {
        FileIO fileIO = new FileIO();
        fileIO.setFileService(fileIoService);
        assertEquals(fileIoService, fileIO.getFileService());
    }

    @Test
    void test_createWantedWOrkers() {
        FileIO theFIleIO = new FileIO(new WorkerFileIOService());
        theFIleIO.write(listOfWorkers);
    }

}
