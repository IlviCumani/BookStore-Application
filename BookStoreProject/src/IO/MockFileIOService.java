package IO;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class MockFileIOService implements FileIOServiceInjectable{
    private final ArrayList<Serializable> writtenInD = new ArrayList<>();

    @Override
    public void write(ArrayList<Serializable> listOfSomething) {
        for(Serializable obj: listOfSomething) {
            if (!writtenInD.contains(obj)) {
                writtenInD.add(obj);
            }
        }
    }

    @Override
    public ArrayList<Serializable> read() {
        return this.writtenInD;
    }
}
