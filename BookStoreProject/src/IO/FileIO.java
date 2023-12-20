package IO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class FileIO {

    FileIOServiceInjectable fileService;

    public FileIO(FileIOServiceInjectable fileService) {
        this.fileService = fileService;
    }

    public void write(ArrayList<Serializable> listOFSomething) {
        this.fileService.write(listOFSomething);

    }

    public ArrayList<Serializable> read() {
        return this.fileService.read();

    }
}
