package at.korti.katlonengine.io.datatag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Korti on 14.01.2016.
 */
public class DataTagFloat extends DataTagBase {

    public float data;

    public DataTagFloat(float data) {
        this.data = data;
    }

    @Override
    public void readData(DataInput reader) throws IOException {
        data = reader.readFloat();
    }

    @Override
    public void writeData(DataOutput writer) throws IOException {
        writer.writeFloat(data);
    }

    @Override
    public DataTagBase copy() {
        return new DataTagFloat(data);
    }

    @Override
    public int getId() {
        return 3;
    }
}
