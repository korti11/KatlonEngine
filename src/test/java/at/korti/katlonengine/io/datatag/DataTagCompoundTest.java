package at.korti.katlonengine.io.datatag;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;
import java.util.zip.CheckedOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Korti on 14.01.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataTagCompoundTest {

    private DataTagCompound tagCompound;

    @Before
    public void init() {
        tagCompound = new DataTagCompound();
        tagCompound.setFloat("posX", 2.5f);
        tagCompound.setInt("id", 15);
        tagCompound.setString("text", "hello");
    }

    @Test
    public void t001GetValues_ok() {
        assertThat(tagCompound.getFloat("posX"), is(2.5f));
        assertThat(tagCompound.getInt("id"), is(15));
        assertThat(tagCompound.getString("text"), is("hello"));
    }

    @Test
    public void t002CopyCompound_ok() {
        DataTagCompound tagCompound = (DataTagCompound) this.tagCompound.copy();
        assertThat(tagCompound.getFloat("posX"), is(2.5f));
        assertThat(tagCompound.getInt("id"), is(15));
        assertThat(tagCompound.getString("text"), is("hello"));
    }

    @Test
    public void t100GetValues_fail() {
        assertThat(tagCompound.getFloat("posY"), is(0f));
        assertThat(tagCompound.getInt("idx"), is(0));
        assertThat(tagCompound.getString("test"), is(""));
    }

}
