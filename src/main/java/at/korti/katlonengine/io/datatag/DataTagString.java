package at.korti.katlonengine.io.datatag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Korti on 14.01.2016.
 */
public class DataTagString extends DataTagBase {

    public String data;

    public DataTagString(String data) {
        this.data = data;
    }

    @Override
    public void readData(DataInput reader) throws IOException {
        data = reader.readLine();
    }

    @Override
    public void writeData(DataOutput writer) throws IOException {
        writer.writeChars(data);
    }

    @Override
    public DataTagBase copy() {
        return new DataTagString(data);
    }

    @Override
    public int getId() {
        return 1;
    }
}
