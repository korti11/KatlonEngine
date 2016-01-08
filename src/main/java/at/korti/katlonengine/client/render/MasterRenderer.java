package at.korti.katlonengine.client.render;

import at.korti.katlonengine.client.model.VBOModel;
import at.korti.katlonengine.util.helper.OpenGLHelper;

import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * Created by Korti on 08.01.2016.
 */
public class MasterRenderer {

    private static MasterRenderer instance;

    private List<VBOModel> models;

    private MasterRenderer() {
        models = new LinkedList<>();
    }

    public static MasterRenderer instance() {
        if (instance == null) {
            instance = new MasterRenderer();
        }
        return instance;
    }

    public static void addVBOModel(VBOModel model) {
        instance().models.add(model);
    }

    public void renderAll() {
        for (VBOModel model : models) {
            loadModel(model);
            glLoadIdentity();
            OpenGLHelper.clearFramebuffer();
            glDrawArrays(GL_TRIANGLES, 0, model.getModel().getFaces().size() * 3);
            glBindBuffer(GL_ARRAY_BUFFER, 0);
        }
    }

    private void loadModel(VBOModel model) {
        glBindBuffer(GL_ARRAY_BUFFER, model.getVboVertexHandler());
        glVertexPointer(3, GL_FLOAT, 0, 0L);
        glBindBuffer(GL_ARRAY_BUFFER, model.getVboNormalHandler());
        glNormalPointer(GL_FLOAT, 0, 0L);
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_NORMAL_ARRAY);
    }

    public void cleanUp() {
        models.forEach(at.korti.katlonengine.client.model.VBOModel::cleanUp);
    }

}
