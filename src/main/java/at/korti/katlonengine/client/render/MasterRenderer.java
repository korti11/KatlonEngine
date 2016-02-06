package at.korti.katlonengine.client.render;

import at.korti.katlonengine.client.display.Camera;
import at.korti.katlonengine.client.model.TexturedModel;
import at.korti.katlonengine.client.model.TexturedVAOModel;
import at.korti.katlonengine.client.model.VAOModel;
import at.korti.katlonengine.client.resources.Icon;
import at.korti.katlonengine.client.shader.MasterShader;
import at.korti.katlonengine.components.Light;
import at.korti.katlonengine.util.helper.MatrixHelper;
import at.korti.katlonengine.util.helper.OpenGLHelper;
import at.korti.katlonengine.util.matrix.Matrix4f;
import at.korti.katlonengine.util.vector.Vector3f;

import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Korti on 08.01.2016.
 */
public class MasterRenderer {

    private static MasterRenderer instance;
    public Camera camera;
    public Light light;
    private List<VAOModel> models;
    private MasterShader shader;
    private Matrix4f projectionMatrix;

    private MasterRenderer() {
        models = new LinkedList<>();
    }

    public static MasterRenderer instance() {
        if (instance == null) {
            instance = new MasterRenderer();
        }
        return instance;
    }

    public static void addVBOModel(VAOModel model) {
        instance().models.add(model);
    }

    public void init() {
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        shader = new MasterShader();
        camera = new Camera();
        light = new Light(new Vector3f(-10, 0, 20), new Vector3f(1, 1, 1));
        projectionMatrix = MatrixHelper.projectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.loadLight(light);
        shader.stop();
    }

    public void prepare() {
        glEnable(GL_DEPTH_TEST);
        OpenGLHelper.clearFramebuffer();
        OpenGLHelper.clearColor();
    }

    public void renderAll() {
        prepare();
        for (VAOModel model : models) {
            shader.start();
            model.bindVAO();
            model.enableVertexAttribArray();
            shader.loadTransformationMatrix(MatrixHelper.createTransformationMatrix(new Vector3f(-1f, 0f, -5f), 0, 245, 0, .25f));
            shader.loadViewMatrix(MatrixHelper.createViewMatrix(camera));
            if (model instanceof TexturedVAOModel) {
                TexturedModel texturedModel = (TexturedModel) model.getModel();
                Icon texture = texturedModel.getTexture();
                texture.enable();
//                glActiveTexture(GL_TEXTURE0);
//                glBindTexture(GL_TEXTURE_2D, texturedModel.getTexture().getTextureID());
                texture.loadTexture();
            }
            glDrawElements(GL_TRIANGLES, model.getModel().getFaces().size() * 3, GL_UNSIGNED_INT, 0);
            glDisable(GL_TEXTURE_2D);
            model.disableVertexAttribArray();
            model.unbindVAO();
            shader.stop();
        }
    }

    public void cleanUp() {
        models.forEach(VAOModel::cleanUp);
        shader.cleanUp();
    }

}
