package IO;

import java.io.Serializable;
import java.util.ArrayList;

public interface FileIOServiceInjectable {
    public ArrayList<Serializable> read();

    public void write(ArrayList<Serializable> listOfSomething);
}
