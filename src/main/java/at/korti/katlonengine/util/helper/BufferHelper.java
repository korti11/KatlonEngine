package at.korti.katlonengine.util.helper;

import org.lwjgl.BufferUtils;

import java.nio.*;

/**
 * Created by Korti on 21.01.2016.
 */
public class BufferHelper {

    /**
     * Create a FloatBuffer store the data and flip it.
     *
     * @param data Data that should stored
     * @return FloatBuffer with the data in it
     */
    public static FloatBuffer store(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     * Create a IntBuffer store the data and flip it.
     * @param data Data that should stored
     * @return IntBuffer with the data in it
     */
    public static IntBuffer store(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     * Create a ByteBuffer store the data and flip it.
     * @param data Data that should stored
     * @return ByteBuffer with the data in it
     */
    public static ByteBuffer store(byte[] data) {
        ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     * Create a DoubleBuffer store the data and flip it.
     * @param data Data that should stored
     * @return DoubleBuffer with the data in it
     */
    public static DoubleBuffer store(double[] data) {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     * Create a CharBuffer store the data and flip it.
     * @param data Data that should stored
     * @return CharBuffer with the data in it
     */
    public static CharBuffer store(char[] data) {
        CharBuffer buffer = BufferUtils.createCharBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     * Create a LongBuffer store the data and flip it.
     * @param data Data that should stored
     * @return LongBuffer with the data in it
     */
    public static LongBuffer store(long[] data) {
        LongBuffer buffer = BufferUtils.createLongBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     * Resize the given byte buffer to the new capacity.
     *
     * @param buffer      Source buffer
     * @param newCapacity New capacity
     * @return Resized buffer or the original buffer if the
     * new capacity is smaller then the current capacity.
     */
    public static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        if (newCapacity > buffer.capacity()) {
            ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
            buffer.flip();
            newBuffer.put(buffer);
            return newBuffer;
        }

        return buffer;
    }
}
