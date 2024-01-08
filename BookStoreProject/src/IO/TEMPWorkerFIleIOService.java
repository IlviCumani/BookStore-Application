package IO;

import java.io.*;
import java.util.ArrayList;

public class TEMPWorkerFIleIOService implements FileIOServiceInjectable{


    private File file;
    private final String PATH = "BookStoreProject/tempworkers.dat";

    public TEMPWorkerFIleIOService() {
        this.file = new File(PATH);
    }


    @Override
    public ArrayList<Serializable> read() {
        try {
            FileInputStream fout = new FileInputStream(file);
            ObjectInputStream reader = new ObjectInputStream(fout);
            Object o= reader.readObject();
            reader.close();
            if (o instanceof ArrayList<?>)
                return (ArrayList<Serializable>)o;
            throw new ClassNotFoundException();
        }
        catch (IOException | ClassNotFoundException j) {
            throw new RuntimeException("Not allowed");
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
            System.out.println(e.toString());
            throw new RuntimeException();
        }

    }



}
