package at.korti.katlonengine.io.datatag;

import java.io.*;

/**
 * Created by Korti on 14.01.2016.
 */
public abstract class DataTagBase {

    public static DataTagBase getType(byte id) {
        switch (id) {
            default:
                return null;
        }
    }

    public abstract void readData(DataInput reader) throws IOException;

    public abstract void writeData(DataOutput writer) throws IOException;

    public abstract DataTagBase copy();

    public abstract int getId();

}
