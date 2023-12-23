package IO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class FileIO {

    private FileIOServiceInjectable fileService;

    public FileIO(FileIOServiceInjectable fileService) {
        this.fileService = fileService;
    }

    public FileIO() {

    }

    public void write(ArrayList<Serializable> listOFSomething) {
        this.fileService.write(listOFSomething);
    }

    public ArrayList<Serializable> read() {
        return this.fileService.read();
    }

    public FileIOServiceInjectable getFileService(){
        return this.fileService;
    }

    public void setFileService(FileIOServiceInjectable fileService){
        this.fileService = fileService;
    }
}
