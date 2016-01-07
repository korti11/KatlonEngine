/*
    Created from the Vector3f class of the LWJGL2.
    https://github.com/LWJGL/lwjgl/blob/master/src/java/org/lwjgl/util/vector/Vector3f.java
 */
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

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public float lengthSquare() {
        return x * x + y * y + z * z;
    }

    public Vector3f translate(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public static Vector3f add(Vector3f left, Vector3f right, Vector3f dest) {
        if (dest == null) {
            return new Vector3f(left.x + right.x, left.y + right.y, left.z + right.z);
        } else {
            dest.set(left.x + right.x, left.y + right.y, left.z + right.z);
            return dest;
        }
    }

    public static Vector3f sub(Vector3f left, Vector3f right, Vector3f dest) {
        if (dest == null) {
            return new Vector3f(left.x - right.x, left.y - right.y, left.z - right.z);
        } else {
            dest.set(left.x - right.x, left.y - right.y, left.z - right.z);
            return dest;
        }
    }

    public static Vector3f cross(Vector3f left, Vector3f right, Vector3f dest) {
        if (dest == null) {
            dest = new Vector3f();
        }
        dest.set(
                left.y * right.z - left.z * right.y,
                right.y * left.z - right.z * left.x,
                left.x * right.y - left.y * right.x
        );
        return dest;
    }

    @Override
    public Vector negate() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    public Vector3f negate(Vector3f dest) {
        if (dest == null) {
            dest = new Vector3f();
        }
        dest.x = -x;
        dest.y = -y;
        dest.z = -z;
        return dest;
    }

    public Vector3f normalise(Vector3f dest) {
        float l = length();
        if (dest == null) {
            dest = new Vector3f(x / l, y / l, z / l);
        } else {
            dest.set(x / l, y / l, z / l);
        }
        return dest;
    }

    public static float dot(Vector3f left, Vector3f right) {
        return left.x * right.x + left.y * right.y + left.z * right.z;
    }

    public static float angel(Vector3f a, Vector3f b) {
        float dls = dot(a, b) / (a.length() * b.length());
        if (dls < -1f) {
            dls = -1f;
        } else if (dls > 1.0f) {
            dls = 1.0f;
        }
        return (float) Math.acos(dls);
    }

    @Override
    public Vector load(FloatBuffer buffer) {
        x = buffer.get();
        y = buffer.get();
        z = buffer.get();
        return this;
    }

    @Override
    public Vector store(FloatBuffer buffer) {
        buffer.put(x);
        buffer.put(y);
        buffer.put(z);
        return this;
    }

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

    public final float getY() {
        return y;
    }

    public final float getZ() {
        return z;
    }

    public final void setX(float x) {
        this.x = x;
    }

    public final void setY(float y) {
        this.y = y;
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
