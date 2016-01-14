package at.korti.katlonengine.io.datatag;

import at.korti.katlonengine.KatlonEngine;
import org.apache.logging.log4j.Logger;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Korti on 14.01.2016.
 */
public class DataTagCompound extends DataTagBase {

    private static Logger logger = KatlonEngine.logger;

    private Map<String, DataTagBase> dataMap;

    public DataTagCompound() {
        dataMap = new HashMap<>();
    }

    @Override
    public void readData(DataInput reader) throws IOException {
        dataMap.clear();

        int id;
        while ((id = reader.readInt()) != 0) {
            String key = reader.readLine();
            DataTagBase tag = getType(id);
            tag.readData(reader);
            dataMap.put(key, tag);
        }
    }

    @Override
    public void writeData(DataOutput writer) throws IOException {
        for (Map.Entry<String, DataTagBase> entry : dataMap.entrySet()) {
            writer.write(entry.getValue().getId());
            writer.writeChars(entry.getKey());
            entry.getValue().writeData(writer);
        }
        writer.write(0);
    }

    @Override
    public DataTagBase copy() {
        DataTagCompound tagCompound = new DataTagCompound();
        tagCompound.dataMap.putAll(dataMap);
        return tagCompound;
    }

    @Override
    public int getId() {
        return 10;
    }

    public void setFloat(String key, float data) {
        dataMap.put(key, new DataTagFloat(data));
    }

    public void setInt(String key, int data) {
        dataMap.put(key, new DataTagInt(data));
    }

    public void setString(String key, String data) {
        dataMap.put(key, new DataTagString(data));
    }

    public void setData(String key, DataTagBase tag) {
        dataMap.put(key, tag);
    }

    public float getFloat(String key) {
        try {
            return ((DataTagFloat) dataMap.get(key)).data;
        } catch (NullPointerException e) {
            logger.error("Could not find a data tag with the key: " + key, e);
        } catch (ClassCastException e) {
            logger.error("Could not cast the requested value with the key: " + key + " to float!");
        }
        return 0;
    }

    public int getInt(String key) {
        try {
            return ((DataTagInt) dataMap.get(key)).data;
        } catch (NullPointerException e) {
            logger.error("Could not find a data tag with the key: " + key, e);
        } catch (ClassCastException e) {
            logger.error("Could not cast the requested value with the key: " + key + " to int!");
        }
        return 0;
    }

    public String getString(String key) {
        try {
            return ((DataTagString) dataMap.get(key)).data;
        } catch (NullPointerException e) {
            logger.error("Could not find a data tag with the key: " + key, e);
        } catch (ClassCastException e) {
            logger.error("Could not cast the requested value with the key: " + key + " to string!");
        }
        return "";
    }

    public DataTagBase getTag(String key) {
        try {
            return dataMap.get(key);
        } catch (NullPointerException e) {
            logger.error("Could not find a data tag with the key: " + key, e);
        }
        return null;
    }
}
