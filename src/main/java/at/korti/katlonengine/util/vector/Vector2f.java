package at.korti.katlonengine.util.vector;

import java.nio.FloatBuffer;

/**
 * Created by Korti on 04.02.2016.
 */
public class Vector2f extends Vector {

    public float x, y;

    public Vector2f() {

    }

    public Vector2f(float x, float y) {
        set(x, y);
    }

    public static float dot(Vector2f left, Vector2f right) {
        return left.x * right.x + left.y * right.y;
    }

    public static float angle(Vector2f a, Vector2f b) {
        float dls = dot(a, b) / (a.length() * b.length());
        if (dls < -1f) {
            dls = -1f;
        } else if (dls > 1.0f) {
            dls = 1.0f;
        }
        return (float) Math.acos(dls);
    }

    public static Vector2f add(Vector2f left, Vector2f right, Vector2f dest) {
        if (dest == null) {
            return new Vector2f(left.x + right.x, left.y + right.y);
        } else {
            dest.set(left.x + right.x, left.y + right.y);
            return dest;
        }
    }

    public static Vector2f sub(Vector2f left, Vector2f right, Vector2f dest) {
        if (dest == null) {
            return new Vector2f(left.x - right.x, left.y - right.y);
        } else {
            dest.set(left.x - right.x, left.y - right.y);
            return dest;
        }
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float lengthSquare() {
        return x * x + y * y;
    }

    public Vector2f translate(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    @Override
    public Vector readFromBuffer(FloatBuffer buffer) {
        x = buffer.get();
        y = buffer.get();
        return this;
    }

    @Override
    public Vector negate() {
        this.x = -x;
        this.y = -y;
        return this;
    }

    public Vector2f negate(Vector2f dest) {
        if (dest == null) {
            dest = new Vector2f();
        }
        dest.x = -x;
        dest.y = -y;
        return this;
    }

    public Vector2f normalise(Vector2f dest) {
        float l = length();

        if (dest == null) {
            dest = new Vector2f(x / l, y / l);
        } else {
            dest.set(x / l, y / l);
        }
        return dest;
    }

    @Override
    public Vector writeToBuffer(FloatBuffer buffer) {
        buffer.put(x);
        buffer.put(y);
        return this;
    }

    @Override
    public Vector scale(float scale) {
        x *= scale;
        y *= scale;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);

        sb.append("Vector2f[");
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        sb.append("]");
        return sb.toString();
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Vector2f other = (Vector2f) obj;

        if (x == other.x && y == other.y) return true;

        return false;
    }
}
