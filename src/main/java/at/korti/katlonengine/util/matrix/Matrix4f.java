package at.korti.katlonengine.util.matrix;

import at.korti.katlonengine.util.vector.Vector3f;

import java.nio.FloatBuffer;

/**
 * Created by Korti on 09.01.2016.
 */
public class Matrix4f extends Matrix {

    /**
     * mat00, mat10, mat20, mat30
     * mat01, mat11, mat21, mat31
     * mat02, mat12, mat22, mat32
     * mat03, mat13, mat23, mat33
     */
    public float mat00, mat01, mat02, mat03,
                 mat10, mat11, mat12, mat13,
                 mat20, mat21, mat22, mat23,
                 mat30, mat31, mat32, mat33;

    public Matrix4f() {
        super();
        setIdentity();
    }

    public Matrix4f(final Matrix4f src) {
        super();
        load(src);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(mat00).append(mat01).append(mat02).append(mat03);
        builder.append(mat10).append(mat11).append(mat12).append(mat13);
        builder.append(mat20).append(mat21).append(mat22).append(mat23);
        builder.append(mat30).append(mat31).append(mat32).append(mat33);
        return builder.toString();
    }

    @Override
    public Matrix setIdentity() {
        return setIdentity(this);
    }

    public static Matrix4f setIdentity(Matrix4f matrix4f) {
        matrix4f.mat00 = 1.0f;
        matrix4f.mat01 = 0.0f;
        matrix4f.mat02 = 0.0f;
        matrix4f.mat03 = 0.0f;
        matrix4f.mat10 = 0.0f;
        matrix4f.mat11 = 1.0f;
        matrix4f.mat12 = 0.0f;
        matrix4f.mat13 = 0.0f;
        matrix4f.mat20 = 0.0f;
        matrix4f.mat21 = 0.0f;
        matrix4f.mat22 = 1.0f;
        matrix4f.mat23 = 0.0f;
        matrix4f.mat30 = 0.0f;
        matrix4f.mat31 = 0.0f;
        matrix4f.mat32 = 0.0f;
        matrix4f.mat33 = 1.0f;

        return matrix4f;
    }

    @Override
    public Matrix invert() {
        return invert(this, this);
    }

    public static Matrix4f invert(Matrix4f src, Matrix4f dest) {
        float determinant = src.determinant();

        if (determinant != 0) {
            if (dest == null) {
                dest = new Matrix4f();
            }
            float determinant_inv = 1f / determinant;

            float mat00 = determinant3x3(src.mat11, src.mat12, src.mat13, src.mat21, src.mat22, src.mat23, src.mat31, src.mat32, src.mat33);
            float mat01 = -determinant3x3(src.mat10, src.mat12, src.mat13, src.mat20, src.mat22, src.mat23, src.mat30, src.mat32, src.mat33);
            float mat02 = determinant3x3(src.mat10, src.mat11, src.mat13, src.mat20, src.mat21, src.mat23, src.mat30, src.mat31, src.mat33);
            float mat03 = -determinant3x3(src.mat10, src.mat11, src.mat12, src.mat20, src.mat21, src.mat22, src.mat30, src.mat31, src.mat32);

            float mat10 = -determinant3x3(src.mat01, src.mat02, src.mat03, src.mat21, src.mat22, src.mat23, src.mat31, src.mat32, src.mat33);
            float mat11 = determinant3x3(src.mat00, src.mat02, src.mat03, src.mat20, src.mat22, src.mat23, src.mat30, src.mat32, src.mat33);
            float mat12 = -determinant3x3(src.mat00, src.mat01, src.mat03, src.mat20, src.mat21, src.mat23, src.mat30, src.mat31, src.mat33);
            float mat13 = determinant3x3(src.mat00, src.mat01, src.mat02, src.mat20, src.mat21, src.mat22, src.mat30, src.mat31, src.mat32);

            float mat20 = determinant3x3(src.mat01, src.mat02, src.mat03, src.mat11, src.mat12, src.mat13, src.mat31, src.mat32, src.mat33);
            float mat21 = -determinant3x3(src.mat00, src.mat02, src.mat03, src.mat10, src.mat12, src.mat13, src.mat30, src.mat32, src.mat33);
            float mat22 = determinant3x3(src.mat00, src.mat01, src.mat03, src.mat10, src.mat11, src.mat13, src.mat30, src.mat31, src.mat33);
            float mat23 = -determinant3x3(src.mat00, src.mat01, src.mat02, src.mat10, src.mat11, src.mat12, src.mat30, src.mat31, src.mat32);

            float mat30 = -determinant3x3(src.mat01, src.mat02, src.mat03, src.mat11, src.mat12, src.mat13, src.mat21, src.mat22, src.mat23);
            float mat31 = determinant3x3(src.mat00, src.mat02, src.mat03, src.mat10, src.mat12, src.mat13, src.mat20, src.mat21, src.mat23);
            float mat32 = -determinant3x3(src.mat00, src.mat01, src.mat03, src.mat10, src.mat11, src.mat13, src.mat20, src.mat21, src.mat23);
            float mat33 = determinant3x3(src.mat00, src.mat01, src.mat02, src.mat10, src.mat11, src.mat12, src.mat20, src.mat21, src.mat22);

            dest.mat00 = mat00 * determinant_inv;
            dest.mat11 = mat11 * determinant_inv;
            dest.mat22 = mat22 * determinant_inv;
            dest.mat33 = mat33 * determinant_inv;
            dest.mat01 = mat10 * determinant_inv;
            dest.mat10 = mat01 * determinant_inv;
            dest.mat20 = mat02 * determinant_inv;
            dest.mat02 = mat20 * determinant_inv;
            dest.mat12 = mat21 * determinant_inv;
            dest.mat21 = mat12 * determinant_inv;
            dest.mat03 = mat30 * determinant_inv;
            dest.mat30 = mat03 * determinant_inv;
            dest.mat13 = mat31 * determinant_inv;
            dest.mat31 = mat13 * determinant_inv;
            dest.mat32 = mat23 * determinant_inv;
            dest.mat23 = mat32 * determinant_inv;

            return dest;
        } else {
            return null;
        }
    }

    @Override
    public Matrix load(FloatBuffer buffer) {
        mat00 = buffer.get();
        mat01 = buffer.get();
        mat02 = buffer.get();
        mat03 = buffer.get();
        mat10 = buffer.get();
        mat11 = buffer.get();
        mat12 = buffer.get();
        mat13 = buffer.get();
        mat20 = buffer.get();
        mat21 = buffer.get();
        mat22 = buffer.get();
        mat23 = buffer.get();
        mat30 = buffer.get();
        mat31 = buffer.get();
        mat32 = buffer.get();
        mat33 = buffer.get();
        return this;
    }

    public void load(Matrix4f src) {
        load(src, this);
    }

    public static Matrix4f load(Matrix4f src, Matrix4f dest) {
        if (dest == null) {
            dest = new Matrix4f();
        }
        dest.mat00 = src.mat00;
        dest.mat01 = src.mat01;
        dest.mat02 = src.mat02;
        dest.mat03 = src.mat03;
        dest.mat10 = src.mat10;
        dest.mat11 = src.mat11;
        dest.mat12 = src.mat12;
        dest.mat13 = src.mat13;
        dest.mat20 = src.mat20;
        dest.mat21 = src.mat21;
        dest.mat22 = src.mat22;
        dest.mat23 = src.mat23;
        dest.mat30 = src.mat30;
        dest.mat31 = src.mat31;
        dest.mat32 = src.mat32;
        dest.mat33 = src.mat33;

        return dest;
    }

    @Override
    public Matrix loadTranspose(FloatBuffer buffer) {
        mat00 = buffer.get();
        mat10 = buffer.get();
        mat20 = buffer.get();
        mat30 = buffer.get();
        mat01 = buffer.get();
        mat11 = buffer.get();
        mat21 = buffer.get();
        mat31 = buffer.get();
        mat02 = buffer.get();
        mat12 = buffer.get();
        mat22 = buffer.get();
        mat32 = buffer.get();
        mat03 = buffer.get();
        mat13 = buffer.get();
        mat23 = buffer.get();
        mat33 = buffer.get();
        return this;
    }

    @Override
    public Matrix negate() {
        return negate(this);
    }

    public Matrix4f negate(Matrix4f dest) {
        return negate(this, dest);
    }

    public Matrix4f negate(Matrix4f src, Matrix4f dest) {
        if (dest == null) {
            dest = new Matrix4f();
        }

        dest.mat00 = -src.mat00;
        dest.mat01 = -src.mat01;
        dest.mat02 = -src.mat02;
        dest.mat03 = -src.mat03;
        dest.mat10 = -src.mat10;
        dest.mat11 = -src.mat11;
        dest.mat12 = -src.mat12;
        dest.mat13 = -src.mat13;
        dest.mat20 = -src.mat20;
        dest.mat21 = -src.mat21;
        dest.mat22 = -src.mat22;
        dest.mat23 = -src.mat23;
        dest.mat30 = -src.mat30;
        dest.mat31 = -src.mat31;
        dest.mat32 = -src.mat32;
        dest.mat33 = -src.mat33;

        return dest;
    }

    @Override
    public Matrix store(FloatBuffer buffer) {
        buffer.put(mat00);
        buffer.put(mat01);
        buffer.put(mat02);
        buffer.put(mat03);
        buffer.put(mat10);
        buffer.put(mat11);
        buffer.put(mat12);
        buffer.put(mat13);
        buffer.put(mat20);
        buffer.put(mat21);
        buffer.put(mat22);
        buffer.put(mat23);
        buffer.put(mat30);
        buffer.put(mat31);
        buffer.put(mat32);
        buffer.put(mat33);
        return this;
    }

    @Override
    public Matrix storeTranspose(FloatBuffer buffer) {
        buffer.put(mat00);
        buffer.put(mat10);
        buffer.put(mat20);
        buffer.put(mat30);
        buffer.put(mat01);
        buffer.put(mat11);
        buffer.put(mat21);
        buffer.put(mat31);
        buffer.put(mat02);
        buffer.put(mat12);
        buffer.put(mat22);
        buffer.put(mat32);
        buffer.put(mat03);
        buffer.put(mat13);
        buffer.put(mat23);
        buffer.put(mat33);
        return this;
    }

    public Matrix store3f(FloatBuffer buffer) {
        buffer.put(mat00);
        buffer.put(mat01);
        buffer.put(mat02);
        buffer.put(mat10);
        buffer.put(mat11);
        buffer.put(mat12);
        buffer.put(mat20);
        buffer.put(mat21);
        buffer.put(mat22);
        return this;
    }

    public static Matrix4f add(Matrix4f left, Matrix4f right, Matrix4f dest) {
        if (dest == null) {
            dest = new Matrix4f();
        }

        dest.mat00 = left.mat00 + right.mat00;
        dest.mat01 = left.mat01 + right.mat01;
        dest.mat02 = left.mat02 + right.mat02;
        dest.mat03 = left.mat03 + right.mat03;
        dest.mat10 = left.mat10 + right.mat10;
        dest.mat11 = left.mat11 + right.mat11;
        dest.mat12 = left.mat12 + right.mat12;
        dest.mat13 = left.mat13 + right.mat13;
        dest.mat20 = left.mat20 + right.mat20;
        dest.mat21 = left.mat21 + right.mat21;
        dest.mat22 = left.mat22 + right.mat22;
        dest.mat23 = left.mat23 + right.mat23;
        dest.mat30 = left.mat30 + right.mat30;
        dest.mat31 = left.mat31 + right.mat31;
        dest.mat32 = left.mat32 + right.mat32;
        dest.mat33 = left.mat33 + right.mat33;

        return dest;
    }

    public static Matrix4f sub(Matrix4f left, Matrix4f right, Matrix4f dest) {
        if (dest == null) {
            dest = new Matrix4f();
        }

        dest.mat00 = left.mat00 - right.mat00;
        dest.mat01 = left.mat01 - right.mat01;
        dest.mat02 = left.mat02 - right.mat02;
        dest.mat03 = left.mat03 - right.mat03;
        dest.mat10 = left.mat10 - right.mat10;
        dest.mat11 = left.mat11 - right.mat11;
        dest.mat12 = left.mat12 - right.mat12;
        dest.mat13 = left.mat13 - right.mat13;
        dest.mat20 = left.mat20 - right.mat20;
        dest.mat21 = left.mat21 - right.mat21;
        dest.mat22 = left.mat22 - right.mat22;
        dest.mat23 = left.mat23 - right.mat23;
        dest.mat30 = left.mat30 - right.mat30;
        dest.mat31 = left.mat31 - right.mat31;
        dest.mat32 = left.mat32 - right.mat32;
        dest.mat33 = left.mat33 - right.mat33;

        return dest;
    }

    public static Matrix4f mul(Matrix4f left, Matrix4f right, Matrix4f dest) {
        if (dest == null) {
            dest = new Matrix4f();
        }

        dest.mat00 = left.mat00 * right.mat00 + left.mat10 * right.mat01 + left.mat20 * right.mat02 + left.mat30 * right.mat03;
        dest.mat01 = left.mat01 * right.mat00 + left.mat11 * right.mat01 + left.mat21 * right.mat02 + left.mat31 * right.mat03;
        dest.mat02 = left.mat02 * right.mat00 + left.mat12 * right.mat01 + left.mat22 * right.mat02 + left.mat32 * right.mat03;
        dest.mat03 = left.mat03 * right.mat00 + left.mat13 * right.mat01 + left.mat23 * right.mat02 + left.mat33 * right.mat03;
        dest.mat10 = left.mat00 * right.mat10 + left.mat10 * right.mat11 + left.mat20 * right.mat12 + left.mat30 * right.mat13;
        dest.mat11 = left.mat01 * right.mat10 + left.mat11 * right.mat11 + left.mat21 * right.mat12 + left.mat31 * right.mat13;
        dest.mat12 = left.mat02 * right.mat10 + left.mat12 * right.mat11 + left.mat22 * right.mat12 + left.mat32 * right.mat13;
        dest.mat13 = left.mat03 * right.mat10 + left.mat13 * right.mat11 + left.mat23 * right.mat12 + left.mat33 * right.mat13;
        dest.mat20 = left.mat00 * right.mat20 + left.mat10 * right.mat21 + left.mat20 * right.mat22 + left.mat30 * right.mat23;
        dest.mat21 = left.mat01 * right.mat20 + left.mat11 * right.mat21 + left.mat21 * right.mat22 + left.mat31 * right.mat23;
        dest.mat22 = left.mat02 * right.mat20 + left.mat12 * right.mat21 + left.mat22 * right.mat22 + left.mat32 * right.mat23;
        dest.mat23 = left.mat03 * right.mat20 + left.mat13 * right.mat21 + left.mat23 * right.mat22 + left.mat33 * right.mat23;
        dest.mat30 = left.mat00 * right.mat30 + left.mat10 * right.mat31 + left.mat20 * right.mat32 + left.mat30 * right.mat33;
        dest.mat31 = left.mat01 * right.mat30 + left.mat11 * right.mat31 + left.mat21 * right.mat32 + left.mat31 * right.mat33;
        dest.mat32 = left.mat02 * right.mat30 + left.mat12 * right.mat31 + left.mat22 * right.mat32 + left.mat32 * right.mat33;
        dest.mat33 = left.mat03 * right.mat30 + left.mat13 * right.mat31 + left.mat23 * right.mat32 + left.mat33 * right.mat33;

        return dest;
    }

    @Override
    public Matrix transpose() {
        return transpose(this);
    }

    public Matrix4f transpose(Matrix4f dest) {
        return transpose(this, dest);
    }

    public static Matrix4f transpose(Matrix4f src, Matrix4f dest) {
        if (dest == null) {
            dest = new Matrix4f();
        }

        dest.mat00 = src.mat00;
        dest.mat01 = src.mat10;
        dest.mat02 = src.mat20;
        dest.mat03 = src.mat30;
        dest.mat10 = src.mat01;
        dest.mat11 = src.mat11;
        dest.mat12 = src.mat21;
        dest.mat13 = src.mat31;
        dest.mat20 = src.mat02;
        dest.mat21 = src.mat12;
        dest.mat22 = src.mat22;
        dest.mat23 = src.mat32;
        dest.mat30 = src.mat03;
        dest.mat31 = src.mat31;
        dest.mat32 = src.mat32;
        dest.mat33 = src.mat33;

        return dest;
    }

    @Override
    public Matrix setZero() {
        return setZero(this);
    }

    public static Matrix4f setZero(Matrix4f matrix4f) {
        matrix4f.mat00 = 0.0f;
        matrix4f.mat01 = 0.0f;
        matrix4f.mat02 = 0.0f;
        matrix4f.mat03 = 0.0f;
        matrix4f.mat10 = 0.0f;
        matrix4f.mat11 = 0.0f;
        matrix4f.mat12 = 0.0f;
        matrix4f.mat13 = 0.0f;
        matrix4f.mat20 = 0.0f;
        matrix4f.mat21 = 0.0f;
        matrix4f.mat22 = 0.0f;
        matrix4f.mat23 = 0.0f;
        matrix4f.mat30 = 0.0f;
        matrix4f.mat31 = 0.0f;
        matrix4f.mat32 = 0.0f;
        matrix4f.mat33 = 0.0f;

        return matrix4f;
    }

    public Matrix4f translate(Vector3f vec) {
        return translate(vec, this);
    }

    public Matrix4f translate(Vector3f vec, Matrix4f dest) {
        return translate(vec, this, dest);
    }

    public static Matrix4f translate(Vector3f vec, Matrix4f src, Matrix4f dest) {
        if (dest == null) {
            dest = new Matrix4f();
        }

        dest.mat30 += src.mat00 * vec.x + src.mat10 * vec.y + src.mat20 * vec.z;
        dest.mat31 += src.mat01 * vec.x + src.mat11 * vec.y + src.mat21 * vec.z;
        dest.mat32 += src.mat02 * vec.x + src.mat12 * vec.y + src.mat22 * vec.z;
        dest.mat33 += src.mat03 * vec.x + src.mat13 * vec.y + src.mat23 * vec.z;

        return dest;
    }

    public Matrix4f scale(Vector3f vec) {
        return scale(vec, this, this);
    }

    public static Matrix4f scale(Vector3f vec, Matrix4f src, Matrix4f dest) {
        if (dest == null) {
            dest = new Matrix4f();
        }

        dest.mat00 = src.mat00 * vec.x;
        dest.mat01 = src.mat01 * vec.x;
        dest.mat02 = src.mat02 * vec.x;
        dest.mat03 = src.mat03 * vec.x;
        dest.mat10 = src.mat10 * vec.y;
        dest.mat11 = src.mat11 * vec.y;
        dest.mat12 = src.mat12 * vec.y;
        dest.mat13 = src.mat13 * vec.y;
        dest.mat20 = src.mat20 * vec.z;
        dest.mat21 = src.mat21 * vec.z;
        dest.mat22 = src.mat22 * vec.z;
        dest.mat23 = src.mat23 * vec.z;

        return dest;
    }

    public Matrix4f rotate(float angle, Vector3f axis) {
        return rotate(angle, axis, this);
    }

    public Matrix4f rotate(float angle, Vector3f axis, Matrix4f dest) {
        return rotate(angle, axis, this, dest);
    }

    public static Matrix4f rotate(float angle, Vector3f axis, Matrix4f src, Matrix4f dest) {
        if (dest == null) {
            dest = new Matrix4f();
        }

        float c = (float) Math.cos((double)angle);
        float s = (float) Math.sin((double) angle);
        float oC = 1.0f - c;
        float xy = axis.x * axis.y;
        float yz = axis.y * axis.z;
        float xz = axis.x * axis.z;
        float xs = axis.x * s;
        float ys = axis.y * s;
        float zs = axis.z * s;

        float f00 = axis.x * axis.x * oC + c;
        float f01 = xy * oC + zs;
        float f02 = xz * oC - ys;

        float f10 = xy * oC - zs;
        float f11 = axis.y * axis.y * oC + c;
        float f12 = yz * oC + xs;

        float f20 = xz * oC + ys;
        float f21 = yz * oC - xs;
        float f22 = axis.z * axis.z * oC + c;

        float t00 = src.mat00 * f00 + src.mat10 * f01 + src.mat20 * f02;
        float t01 = src.mat01 * f00 + src.mat11 * f01 + src.mat21 * f02;
        float t02 = src.mat02 * f00 + src.mat12 * f01 + src.mat22 * f02;
        float t03 = src.mat03 * f00 + src.mat13 * f01 + src.mat23 * f02;
        float t10 = src.mat00 * f10 + src.mat10 * f11 + src.mat20 * f12;
        float t11 = src.mat01 * f10 + src.mat11 * f11 + src.mat21 * f12;
        float t12 = src.mat02 * f10 + src.mat12 * f11 + src.mat22 * f12;
        float t13 = src.mat03 * f10 + src.mat13 * f11 + src.mat23 * f12;
        dest.mat20 = src.mat00 * f20 + src.mat10 * f21 + src.mat20 * f22;
        dest.mat21 = src.mat01 * f20 + src.mat11 * f21 + src.mat21 * f22;
        dest.mat22 = src.mat02 * f20 + src.mat12 * f21 + src.mat22 * f22;
        dest.mat23 = src.mat03 * f20 + src.mat13 * f21 + src.mat23 * f22;
        dest.mat00 = t00;
        dest.mat01 = t01;
        dest.mat02 = t02;
        dest.mat03 = t03;
        dest.mat10 = t10;
        dest.mat11 = t11;
        dest.mat12 = t12;
        dest.mat13 = t13;

        return dest;
    }

    @Override
    public float determinant() {
        float f = mat00 * ((mat11 * mat22 * mat33 + mat12 * mat23 * mat31 + mat13 * mat21 * mat32)
                - mat13 * mat22 * mat30
                - mat11 * mat23 * mat32
                - mat12 * mat21 * mat33);

        f -= mat01 * ((mat10 * mat22 * mat33 + mat12 * mat23 * mat30 + mat13 * mat20 * mat32)
                - mat13 * mat21 * mat30
                - mat10 * mat23 * mat31
                - mat12 * mat20 * mat33);

        f -= mat02 * ((mat10 * mat21 * mat33 + mat11 * mat23 * mat30 + mat13 + mat20 + mat31)
                - mat13 * mat21 * mat30
                - mat10 * mat23 * mat31
                - mat11 * mat20 * mat33);

        f -= mat03 * ((mat10 * mat21 * mat32 + mat11 * mat22 * mat30 + mat12 * mat20 * mat31)
                - mat12 * mat21 * mat30
                - mat10 * mat22 * mat31
                - mat11 * mat20 * mat32);
        return f;
    }

    private static float determinant3x3(float mat00, float mat01, float mat02,
                                        float mat10, float mat11, float mat12,
                                        float mat20, float mat21, float mat22) {
        return mat00 * (mat11 * mat22 - mat12 * mat21)
                + mat01 * (mat12 * mat20 - mat10 * mat22)
                + mat02 * (mat10 * mat21 - mat11 * mat20);
    }


}