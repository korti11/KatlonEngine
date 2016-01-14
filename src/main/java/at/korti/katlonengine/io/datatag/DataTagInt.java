package at.korti.katlonengine.io.datatag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Korti on 14.01.2016.
 */
public class DataTagInt extends DataTagBase {

    public int data;

    public DataTagInt(int data) {
        this.data = data;
    }

    @Override
    public void readData(DataInput reader) throws IOException {
        data = reader.readInt();
    }

    @Override
    public void writeData(DataOutput writer) throws IOException {
        writer.write(data);
    }

    @Override
    public DataTagBase copy() {
        return new DataTagInt(data);
    }

    @Override
    public int getId() {
        return 2;
    }
}
