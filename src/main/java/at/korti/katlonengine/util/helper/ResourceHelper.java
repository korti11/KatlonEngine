package at.korti.katlonengine.util.helper;

import java.io.*;
import java.net.URL;

/**
 * Created by Korti on 07.01.2016.
 */
public class ResourceHelper {

    public static URL getUrl(String path) {
        return ResourceHelper.class.getClassLoader().getResource(path);
    }

    public static File getFile(String path) {
        return new File(getUrl(path).getFile());
    }

    public static FileReader getFileReader(String path) throws FileNotFoundException {
        return new FileReader(getFile(path));
    }

    public static BufferedReader getBufferedReaderForFile(String path) throws FileNotFoundException {
        return new BufferedReader(getFileReader(path));
    }

    public static InputStream getFileAsStream(String path) {
        return ResourceHelper.class.getClassLoader().getResourceAsStream(path);
    }

}
