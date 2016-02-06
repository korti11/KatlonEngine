package at.korti.katlonengine.util.helper;

import at.korti.katlonengine.client.resources.Icon;
import org.lwjgl.BufferUtils;

import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

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

    /**
     * Get a Texture of the path of the resource.
     *
     * @param path Path to the texture in the resource folder
     * @return Texture
     * @throws IOException
     */
    public static Icon getTexture(String path) throws IOException {
        return new Icon(getImageAsBuffer(path, 8 * 1024));
    }

    private static ByteBuffer getImageAsBuffer(String path, int bufferSize) throws IOException {
        ByteBuffer buffer;
        File file = getFile(path);
        if (file.isFile()) {
            FileInputStream inputStream = new FileInputStream(file);
            FileChannel fileChannel = inputStream.getChannel();
            buffer = BufferUtils.createByteBuffer((int) (fileChannel.size() + 1));

            while (fileChannel.read(buffer) != -1) ;

            fileChannel.close();
            inputStream.close();
        } else {
            buffer = BufferUtils.createByteBuffer(bufferSize);
            InputStream source = getFileAsStream(path);
            if (source == null) {
                throw new FileNotFoundException(path);
            }
            try {
                ReadableByteChannel byteChannel = Channels.newChannel(source);
                try {
                    for (; ; ) {
                        int bytes = byteChannel.read(buffer);
                        if (bytes == -1) {
                            break;
                        }
                        if (buffer.remaining() == 0) {
                            buffer = BufferHelper.resizeBuffer(buffer, buffer.capacity() * 2);
                        }
                    }
                } finally {
                    byteChannel.close();
                }
            } finally {
                source.close();
            }
        }
        buffer.flip();
        return buffer;
    }

}
