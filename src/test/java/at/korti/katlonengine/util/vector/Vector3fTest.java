package at.korti.katlonengine.util.vector;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Korti on 07.01.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Vector3fTest {

    private float x, y, z, x2, y2, z2;

    @Before
    public void init() {
        x = 1.5f;
        y = 6.5f;
        z = 0.5f;
        x2 = 5.3f;
        y2 = 9.1f;
        z2 = -0.6f;
    }

    @Test
    public void t001CreateVectorAndSet_ok() {
        Vector3f vector3f = new Vector3f(x, y, z);
        assertThat(vector3f.getX(), is(x));
        assertThat(vector3f.getY(), is(y));
        assertThat(vector3f.getZ(), is(z));
        x = 9.5f;
        y = 3.0f;
        z = 1.9f;
        vector3f.set(x, y, z);
        assertThat(vector3f.getX(), is(x));
        assertThat(vector3f.getY(), is(y));
        assertThat(vector3f.getZ(), is(z));
    }

    @Test
    public void t002ScaleVector_ok() {
        float scale = 2;
        Vector3f vector3f = new Vector3f(x, y, z);
        vector3f.scale(scale);
        assertThat(vector3f.getX(), is(x * 2));
        assertThat(vector3f.getY(), is(y * 2));
        assertThat(vector3f.getZ(), is(z * 2));
        x = 2.0f;
        y = 13.6f;
        z = 5.4f;
        vector3f.set(x, y, z);
        scale = 3.2f;
        vector3f.scale(scale);
        assertThat(vector3f.getX(), is(x * scale));
        assertThat(vector3f.getY(), is(y * scale));
        assertThat(vector3f.getZ(), is(z * scale));
    }

    @Test
    public void t003TranslateVector_ok() {
        float translateX = 2;
        float translateY = 6;
        float translateZ = 1;
        Vector3f vector3f = new Vector3f(x, y, z);
        vector3f.translate(translateX, translateY, translateZ);
        assertThat(vector3f.getX(), is(x + translateX));
        assertThat(vector3f.getY(), is(y + translateY));
        assertThat(vector3f.getZ(), is(z + translateZ));
        vector3f.set(x, y, z);
        translateX = 8;
        translateY = 3.5f;
        translateZ = -1.2f;
        vector3f.translate(translateX, translateY, translateZ);
        assertThat(vector3f.getX(), is(x + translateX));
        assertThat(vector3f.getY(), is(y + translateY));
        assertThat(vector3f.getZ(), is(z + translateZ));
    }

    @Test
    public void t004NegateVector_ok() {
        Vector3f vector3f = new Vector3f(x, y, z);
        vector3f.negate();
        assertThat(vector3f.getX(), is(-x));
        assertThat(vector3f.getY(), is(-y));
        assertThat(vector3f.getZ(), is(-z));
        vector3f = new Vector3f(x, y, z);
        vector3f.negate(vector3f);
        assertThat(vector3f.getX(), is(-x));
        assertThat(vector3f.getY(), is(-y));
        assertThat(vector3f.getZ(), is(-z));
    }

    @Test
    public void t005NormaliseVector_ok() {
        Vector3f vector3f = new Vector3f(x, y, z);
        float length = (float) Math.sqrt((x * x) + (y * y) + (z * z));
        length = length < 0 ? -length : length;
        vector3f.normalise();
        assertThat(vector3f.getX(), is(x / length));
        assertThat(vector3f.getY(), is(y / length));
        assertThat(vector3f.getZ(), is(z / length));
        vector3f = new Vector3f(x, y, z);
        vector3f.normalise(vector3f);
        assertThat(vector3f.getX(), is(x / length));
        assertThat(vector3f.getY(), is(y / length));
        assertThat(vector3f.getZ(), is(z / length));
    }

    @Test
    public void t006AddVectorToAnotherVector_ok() {
        Vector3f vector3f = new Vector3f(x, y, z);
        Vector3f vector3f1 = new Vector3f(x2, y2, z2);
        Vector3f outCome = Vector3f.add(vector3f, vector3f1, null);
        assertThat(outCome.getX(), is(x + x2));
        assertThat(outCome.getY(), is(y + y2));
        assertThat(outCome.getZ(), is(z + z2));
        vector3f = new Vector3f(x, y, z);
        vector3f1 = new Vector3f(x2, y2, z2);
        Vector3f.add(vector3f1, vector3f, outCome);
        assertThat(outCome.getX(), is(x2 + x));
        assertThat(outCome.getY(), is(y2 + y));
        assertThat(outCome.getZ(), is(z2 + z));
    }

    @Test
    public void t007SubtractVectorOfAnotherVector_ok() {
        Vector3f vector3f = new Vector3f(x, y, z);
        Vector3f vector3f1 = new Vector3f(x2, y2, z2);
        Vector3f outCome = Vector3f.sub(vector3f, vector3f1, null);
        assertThat(outCome.getX(), is(x - x2));
        assertThat(outCome.getY(), is(y - y2));
        assertThat(outCome.getZ(), is(z - z2));
        vector3f = new Vector3f(x, y, z);
        vector3f1 = new Vector3f(x2, y2, z2);
        Vector3f.sub(vector3f1, vector3f, outCome);
        assertThat(outCome.getX(), is(x2 - x));
        assertThat(outCome.getY(), is(y2 - y));
        assertThat(outCome.getZ(), is(z2 - z));
    }

    @Test
    public void t008MultiplyVectorToAnotherVector_ok() {
        Vector3f vector3f = new Vector3f(x, y, z);
        Vector3f vector3f1 = new Vector3f(x2, y2, z2);
        float outCome = Vector3f.dot(vector3f, vector3f1);
        assertThat(outCome, is((x * x2) + (y * y2) + (z * z2)));
        vector3f = new Vector3f(x, y, z);
        vector3f1 = new Vector3f(x2, y2, z2);
        outCome = Vector3f.dot(vector3f1, vector3f);
        assertThat(outCome, is((x * x2) + (y * y2) + (z * z2)));
    }

    @Test
    public void t009AngleBetweenTwoVectors_ok() {
        Vector3f vector3f = new Vector3f(x, y, z);
        Vector3f vector3f1 = new Vector3f(x2, y2, z2);
        float length = (float) Math.sqrt((x * x) + (y * y) + (z * z));
        length = length < 0 ? -length : length;
        float length2 = (float) Math.sqrt((x2 * x2) + (y2 * y2) + (z2 * z2));
        length2 = length2 < 0 ? -length2 : length2;
        float dls = ((x * x2) + (y * y2) + (z * z2)) / (length * length2);
        if (dls < -1f) {
            dls = -1f;
        } else if (dls > 1.0f) {
            dls = 1.0f;
        }
        float angle = (float) Math.acos(dls);
        assertThat(Vector3f.angel(vector3f, vector3f1), is(angle));
    }

    @Test
    public void t010CrossProductOfTwoVectors_ok() {
        Vector3f vector3f = new Vector3f(x, y, z);
        Vector3f vector3f1 = new Vector3f(x2, y2, z2);
        Vector3f outCome = Vector3f.cross(vector3f, vector3f1, null);
        assertThat(outCome.getX(), is(y * z2 - z * y2));
        assertThat(outCome.getY(), is(y2 * z - z2 * x));
        assertThat(outCome.getZ(), is(x * y2 - y * x2));
        Vector3f.cross(vector3f1, vector3f, outCome);
        assertThat(outCome.getX(), is(y2 * z - z2 * y));
        assertThat(outCome.getY(), is(y * z2 - z * x2));
        assertThat(outCome.getZ(), is(x2 * y - y2 * x));
    }

    @Test
    public void t011AreTheyEqual_ok() {
        Vector3f vector3f = new Vector3f(x, y, z);
        assertThat(vector3f.equals(vector3f), is(true));
        assertThat(vector3f.equals(null), is(false));
        Vector3f vector3f1 = new Vector3f(x, y, z);
        assertThat(vector3f.equals(vector3f1), is(true));
        vector3f1.set(x2, y2, z2);
        assertThat(vector3f.equals(vector3f1), is(false));
    }

}
