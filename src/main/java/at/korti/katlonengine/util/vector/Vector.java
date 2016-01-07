package at.korti.katlonengine.util.vector;

import java.nio.FloatBuffer;

/**
 * Created by Korti on 07.01.2016.
 */
public abstract class Vector {

    protected Vector() {
        super();
    }

    public final float length() {
        return (float) Math.sqrt(lengthSquare());
    }

    public abstract float lengthSquare();

    public abstract Vector load(FloatBuffer buffer);

    public abstract Vector negate();

    public final Vector normalise() {
        float len = length();
        if (len != 0.0f) {
            float l = 1.0f / len;
            return scale(l);
        } else {
            throw new IllegalStateException("Vector have a length of zero!");
        }
    }

    public abstract Vector store(FloatBuffer buffer);

    public abstract Vector scale(float scale);

}
