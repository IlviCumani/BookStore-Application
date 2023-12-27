package IO;

import java.io.*;
import java.util.ArrayList;

public class BookFileIOService implements FileIOServiceInjectable{

    private final String PATH = "BookStoreProject/temp-books.dat";
    private File file;

    public BookFileIOService() {
        file = new File(PATH);
    }

    @Override
    public ArrayList<Serializable> read() {
        try{
            FileInputStream fileInputStream = new FileInputStream(PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object o =  objectInputStream.readObject();
            objectInputStream.close();
            if (o instanceof ArrayList<?>) {
                return (ArrayList<Serializable>)o;
            }
            throw new ClassNotFoundException();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(ArrayList<Serializable> listOfSomething) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listOfSomething);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
