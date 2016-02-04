package at.korti.katlonengine.io.datatag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Korti on 14.01.2016.
 */
public abstract class DataTagBase {

    public static DataTagBase getType(int id) {
        switch (id) {
            default:
                return null;
        }
    }

    /**
     * Read the value/values for the data tag of the reader.
     *
     * @param reader Reader
     * @throws IOException
     */
    public abstract void readData(DataInput reader) throws IOException;

    /**
     * Write the value/values of the data tag to the writer.
     * @param writer Writer
     * @throws IOException
     */
    public abstract void writeData(DataOutput writer) throws IOException;

    /**
     * Create a exact copy of the data tag.
     * @return Copy of the data tag.
     */
    public abstract DataTagBase copy();

    public abstract int getId();

}
