package at.korti.katlonengine.util.matrix;

import java.nio.FloatBuffer;

/**
 * Created by Korti on 09.01.2016.
 */
public abstract class Matrix {

    protected Matrix() {
        super();
    }

    public abstract Matrix setIdentity();

    public abstract Matrix invert();

    public abstract Matrix load(FloatBuffer buffer);

    public abstract Matrix loadTranspose(FloatBuffer buffer);

    public abstract Matrix negate();

    public abstract Matrix store(FloatBuffer buffer);

    public abstract Matrix storeTranspose(FloatBuffer buffer);

    public abstract Matrix transpose();

    public abstract Matrix setZero();

    public abstract float determinant();

}
