package at.korti.katlonengine.util.helper;

import java.io.*;
import java.net.URL;

/**
 * Created by Korti on 07.01.2016.
 */
public class ResourceHelper {

    /**
     * Get the url of the relevant path of the resource.
     *
     * @param path Path to the file in the resource folder
     * @return Url with the relevant path
     */
    public static URL getUrl(String path) {
        return ResourceHelper.class.getClassLoader().getResource(path);
    }

    /**
     * Get the file of the path in the resource folder.
     * @param path Path to the file in the resource folder
     * @return File with the relevant path
     */
    public static File getFile(String path) {
        return new File(getUrl(path).getFile());
    }

    /**
     * Get a FileReader of the path of the resource.
     * @param path Path to the file in the resource folder
     * @return FileReader with the file of the resource folder
     * @throws FileNotFoundException
     */
    public static FileReader getFileReader(String path) throws FileNotFoundException {
        return new FileReader(getFile(path));
    }

    /**
     * Get a BufferedReader of the path of the resource.
     * @param path Path to the file in the resource folder
     * @return BufferedReader with the file of the resource folder
     * @throws FileNotFoundException
     */
    public static BufferedReader getBufferedReaderForFile(String path) throws FileNotFoundException {
        return new BufferedReader(getFileReader(path));
    }

    /**
     * Get a InputStream of the path of the resource.
     * @param path Path to the file in the resource folder
     * @return InputStream of the file
     */
    public static InputStream getFileAsStream(String path) {
        return ResourceHelper.class.getClassLoader().getResourceAsStream(path);
    }

}
