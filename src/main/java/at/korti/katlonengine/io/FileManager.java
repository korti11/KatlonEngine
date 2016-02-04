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

    /**
     * Write or overwrite the file with the data tag compound.
     *
     * @param path     Path to the file
     * @param compound Data that is writen to the file
     */
    public static void writeTagCompoundToFile(String path, DataTagCompound compound) {
        try {
            compound.writeData(new DataOutputStream(new FileOutputStream(path)));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Append the data tag compound to the file.
     * @param path Path to the file
     * @param compound Data that is append to the file
     */
    public static void appendTagCompoundToFile(String path, DataTagCompound compound) {
        try {
            compound.writeData(new DataOutputStream(new FileOutputStream(path, true)));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Write or overwrite the file with the data tag list.
     * @param path Path to the file
     * @param list Data that is writen to the file
     */
    public static void writeTagListToFile(String path, DataTagList list) {
        try {
            list.writeData(new DataOutputStream(new FileOutputStream(path)));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Append the data tag list to the file.
     * @param path Path to the file
     * @param list Data that is append to the file
     */
    public static void appendTagListToFile(String path, DataTagList list) {
        try {
            list.writeData(new DataOutputStream(new FileOutputStream(path, true)));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
