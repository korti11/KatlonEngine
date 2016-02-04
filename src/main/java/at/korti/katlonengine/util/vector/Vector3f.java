package at.korti.katlonengine.util.vector;

import java.nio.FloatBuffer;

/**
 * Created by Korti on 07.01.2016.
 */
public class Vector3f extends Vector {

    public float x, y, z;

    public Vector3f() {
        super();
    }

    public Vector3f(float x, float y, float z) {
        set(x, y, z);
    }

    /**
     * Add the right vector to the left vector and stores it in the destination vector.
     *
     * @param vecLeft  Left vector
     * @param vecRight Right vector
     * @param vecDest  Destination vector
     * @return Destination vector
     */
    public static Vector3f add(Vector3f vecLeft, Vector3f vecRight, Vector3f vecDest) {
        if (vecDest == null) {
            return new Vector3f(vecLeft.x + vecRight.x, vecLeft.y + vecRight.y, vecLeft.z + vecRight.z);
        } else {
            vecDest.set(vecLeft.x + vecRight.x, vecLeft.y + vecRight.y, vecLeft.z + vecRight.z);
            return vecDest;
        }
    }

    /**
     * Subtract the right vector of the left vector and stores it in the destination vector.
     * @param vecLeft Left vector
     * @param vecRight Right vector
     * @param vecDest Destination vector
     * @return Destination vector
     */
    public static Vector3f sub(Vector3f vecLeft, Vector3f vecRight, Vector3f vecDest) {
        if (vecDest == null) {
            return new Vector3f(vecLeft.x - vecRight.x, vecLeft.y - vecRight.y, vecLeft.z - vecRight.z);
        } else {
            vecDest.set(vecLeft.x - vecRight.x, vecLeft.y - vecRight.y, vecLeft.z - vecRight.z);
            return vecDest;
        }
    }

    /**
     * Calculate the cross product of the left vector and the right vector
     * and stores it in the destination vector.
     * @param vecLeft Left vector
     * @param vecRight Right vector
     * @param vecDest Destination vector
     * @return Destination vector
     */
    public static Vector3f cross(Vector3f vecLeft, Vector3f vecRight, Vector3f vecDest) {
        if (vecDest == null) {
            vecDest = new Vector3f();
        }
        vecDest.set(
                vecLeft.y * vecRight.z - vecLeft.z * vecRight.y,
                vecRight.y * vecLeft.z - vecRight.z * vecLeft.x,
                vecLeft.x * vecRight.y - vecLeft.y * vecRight.x
        );
        return vecDest;
    }

    /**
     * Calculate the dot product of the left and right vector
     * and stores it in the destination vector.
     *
     * @param vecLeft
     * @param vecRight
     * @return
     */
    public static float dot(Vector3f vecLeft, Vector3f vecRight) {
        return vecLeft.x * vecRight.x + vecLeft.y * vecRight.y + vecLeft.z * vecRight.z;
    }

    /**
     * Calculate the angle between the vector a and vector b.
     *
     * @param a Vector
     * @param b Vector
     * @return Angle between vector a and vector b
     */
    public static float angel(Vector3f a, Vector3f b) {
        float dls = dot(a, b) / (a.length() * b.length());
        if (dls < -1f) {
            dls = -1f;
        } else if (dls > 1.0f) {
            dls = 1.0f;
        }
        return (float) Math.acos(dls);
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Calculate the square length.
     * @return Square length
     */
    @Override
    public float lengthSquare() {
        return x * x + y * y + z * z;
    }

    /**
     * Translate the vector.
     *
     * @param x Translate n on the x axis
     * @param y Translate n on the y axis
     * @param z Translate n on the z axis
     * @return Translated vector
     */
    public Vector3f translate(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    /**
     * Negate the vector.
     * @return Negate vector
     */
    @Override
    public Vector negate() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    /**
     * Negate the vector and stores it in the destination vector.
     * @param vecDest Destination vector
     * @return Destination vector
     */
    public Vector3f negate(Vector3f vecDest) {
        if (vecDest == null) {
            vecDest = new Vector3f();
        }
        vecDest.x = -x;
        vecDest.y = -y;
        vecDest.z = -z;
        return vecDest;
    }

    /**
     * Normalise the vector and stores it in the destination vector.
     * @param vecDest Destination vector
     * @return Destination vector
     */
    public Vector3f normalise(Vector3f vecDest) {
        float l = length();
        if (vecDest == null) {
            vecDest = new Vector3f(x / l, y / l, z / l);
        } else {
            vecDest.set(x / l, y / l, z / l);
        }
        return vecDest;
    }

    /**
     * Read the vector of the float buffer.
     * @param buffer Float buffer
     * @return Vector
     */
    @Override
    public Vector readFromBuffer(FloatBuffer buffer) {
        x = buffer.get();
        y = buffer.get();
        z = buffer.get();
        return this;
    }

    /**
     * Write the vector to the float buffer.
     * @param buffer Float buffer
     * @return Vector
     */
    @Override
    public Vector writeToBuffer(FloatBuffer buffer) {
        buffer.put(x);
        buffer.put(y);
        buffer.put(z);
        return this;
    }

    /**
     * Scale all three axis of the scale.
     * @param scale Scale
     * @return Scaled vector
     */
    @Override
    public Vector scale(float scale) {
        x *= scale;
        y *= scale;
        z *= scale;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Vector3f[");
        builder.append(x);
        builder.append(", ");
        builder.append(y);
        builder.append(", ");
        builder.append(z);
        builder.append("]");

        return builder.toString();
    }

    public final float getX() {
        return x;
    }

    public final void setX(float x) {
        this.x = x;
    }

    public final float getY() {
        return y;
    }

    public final void setY(float y) {
        this.y = y;
    }

    public final float getZ() {
        return z;
    }

    public final void setZ(float z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Vector3f vec = (Vector3f) obj;

        if(x == vec.x && y == vec.y && z == vec.z) return true;

        return false;
    }
}
