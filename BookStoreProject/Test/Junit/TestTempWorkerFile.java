package Junit;

import IO.FileIO;
import IO.TEMPWorkerFIleIOService;
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

public class TestTempWorkerFile {


    private ArrayList<Serializable> listOfWorkers;

    @BeforeEach
    void setUp() {
        Worker librarian = new Worker("Lib", "librarian", "12345678", "0675850510", null, new Librarian(), 500);
        Worker manager = new Worker("manager", "manager", "12345678", "0675850510", null, new Manager(), 700);
        Worker admin = new Worker("admin", "admin", "12345678", "0675850510", null, new Administrator(), 1000);
        listOfWorkers = new ArrayList<>();
        Worker[] workers = {librarian, manager, admin};
        listOfWorkers.addAll(List.of(workers));

    }

    @Test
    void test_writeInATempFile() {
        TEMPWorkerFIleIOService fIleIOService = new TEMPWorkerFIleIOService();
        FileIO fileIO = new FileIO(fIleIOService);
        fileIO.write(listOfWorkers);
        assertEquals(listOfWorkers, fileIO.read());
    }

    @Test
    void test_rewriteInATempFileAfterAddingAWorker() {
        TEMPWorkerFIleIOService fIleIOService = new TEMPWorkerFIleIOService();
        FileIO fileIO = new FileIO(fIleIOService);
        fileIO.write(listOfWorkers);
        Worker dummyWorker = new Worker();
        listOfWorkers.add(dummyWorker);
        fileIO.write(listOfWorkers);
        assertEquals(listOfWorkers, fileIO.read());
    }

    @Test
    void test_rewriteInATempFileAfterRemovingAWorker() {
        TEMPWorkerFIleIOService fileIOService = new TEMPWorkerFIleIOService();
        FileIO fileIO = new FileIO(fileIOService);
        Worker dummy_worker = new Worker();
        listOfWorkers.add(dummy_worker);
        fileIO.write(listOfWorkers);
        listOfWorkers.remove(dummy_worker);
        fileIO.write(listOfWorkers);
        assertEquals(listOfWorkers, (ArrayList<Serializable>)fileIO.read());
    }




}
