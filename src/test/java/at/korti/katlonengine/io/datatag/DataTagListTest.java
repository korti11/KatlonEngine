package at.korti.katlonengine.io.datatag;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Korti on 14.01.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataTagListTest {

    private DataTagList tagList;

    @Before
    public void init() {
        tagList = new DataTagList();
        tagList.addTag(new DataTagFloat(0.5f));
        tagList.addTag(new DataTagString("hello"));
    }

    @Test
    public void t001CheckSize_ok() {
        assertThat(tagList.size(), is(2));
        tagList.clear();
        assertThat(tagList.size(), is(0));
        tagList.addTag(new DataTagInt(1));
        assertThat(tagList.size(), is(1));
    }

    @Test
    public void t002GetValues_ok() {
        assertThat(((DataTagFloat) tagList.getTag(0)).data, is(0.5f));
        assertThat(((DataTagString) tagList.getTag(1)).data, is("hello"));
        tagList.clear();
        tagList.addTag(new DataTagInt(1));
        assertThat(((DataTagInt) tagList.getTag(0)).data, is(1));
    }

}
