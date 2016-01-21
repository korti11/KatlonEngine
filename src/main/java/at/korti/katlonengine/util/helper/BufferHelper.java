package at.korti.katlonengine.util.helper;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Pointer;

import java.nio.*;

/**
 * Created by Korti on 21.01.2016.
 */
public class BufferHelper {

    public static FloatBuffer store(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static IntBuffer store(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static ByteBuffer store(byte[] data) {
        ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static DoubleBuffer store(double[] data) {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static CharBuffer store(char[] data) {
        CharBuffer buffer = BufferUtils.createCharBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static LongBuffer store(long[] data) {
        LongBuffer buffer = BufferUtils.createLongBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
