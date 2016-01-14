package at.korti.katlonengine.io.datatag;

import java.io.DataInput;
import java.io.DataOutput;

/**
 * Created by Korti on 14.01.2016.
 */
public class DataTagEnd extends DataTagBase {

    @Override
    public void readData(DataInput reader) {

    }

    @Override
    public void writeData(DataOutput writer) {

    }

    @Override
    public DataTagBase copy() {
        return new DataTagEnd();
    }

    @Override
    public int getId() {
        return 0;
    }
}
