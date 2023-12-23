package IO;

import java.io.*;
import java.util.ArrayList;

public class WorkerFIleIOService implements FileIOServiceInjectable{


    private File file;
    private final String PATH = "BookStoreProject/tempworkers.dat";

    public WorkerFIleIOService() {
        this.file = new File(PATH);
    }


    @Override
    public ArrayList<Serializable> read() {
        try {
            FileInputStream fout = new FileInputStream(file);
            ObjectInputStream reader = new ObjectInputStream(fout);
            ArrayList<Serializable> list = (ArrayList<Serializable>)(reader.readObject());
            return list;
        }
        catch (IOException e) {
            throw new IllegalStateException("Not allowed");
        }
        catch (ClassNotFoundException j) {
            throw new IllegalStateException("Not allowedd");
        }

    }

    @Override
    public void write(ArrayList<Serializable> listOfSomething) {
        try {
            FileOutputStream fout = new FileOutputStream(file);
            System.out.println("First Step");
            ObjectOutputStream writer = new ObjectOutputStream(fout);
            System.out.println("Second Step");
            writer.writeObject(listOfSomething);
            System.out.println("Third Step");
            writer.close();
        }
        catch (IOException e) {
            throw new IllegalStateException("Not ALlowed");
        }

    }



}
