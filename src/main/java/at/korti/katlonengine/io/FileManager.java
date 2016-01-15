package at.korti.katlonengine.io;

import at.korti.katlonengine.KatlonEngine;
import at.korti.katlonengine.io.datatag.DataTagCompound;
import at.korti.katlonengine.io.datatag.DataTagList;
import org.apache.logging.log4j.Logger;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Korti on 14.01.2016.
 */
public class FileManager {

    private static Logger logger = KatlonEngine.logger;

    public static void writeTagCompoundToFile(String path, DataTagCompound compound) {
        try {
            compound.writeData(new DataOutputStream(new FileOutputStream(path)));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void appendTagCompoundToFile(String path, DataTagCompound compound) {
        try {
            compound.writeData(new DataOutputStream(new FileOutputStream(path, true)));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void writeTagListToFile(String path, DataTagList list) {
        try {
            list.writeData(new DataOutputStream(new FileOutputStream(path)));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void appendTagListToFile(String path, DataTagList list) {
        try {
            list.writeData(new DataOutputStream(new FileOutputStream(path, true)));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
