package at.korti.katlonengine.io.datatag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Korti on 14.01.2016.
 */
public class DataTagList extends DataTagBase {

    private List<DataTagBase> tags;

    public DataTagList() {
        tags = new LinkedList<>();
    }

    @Override
    public void readData(DataInput reader) throws IOException {
        tags.clear();
        byte id;

        while ((id = reader.readByte()) != 0) {
            DataTagBase tag = getType(id);
            tag.readData(reader);
            tags.add(tag);
        }
    }

    @Override
    public void writeData(DataOutput writer) throws IOException {
        for (DataTagBase tag : tags) {
            writer.write(tag.getId());
            tag.writeData(writer);
        }
    }

    public void addTag(DataTagBase tag) {
        tags.add(tag);
    }

    public DataTagBase getTag(int index) {
        return tags.get(index);
    }

    public int size() {
        return tags.size();
    }

    public void clear() {
        tags.clear();
    }

    @Override
    public DataTagBase copy() {
        DataTagList list = new DataTagList();
        for (DataTagBase tag : tags) {
            list.addTag(tag);
        }
        return list;
    }

    @Override
    public int getId() {
        return 9;
    }
}
