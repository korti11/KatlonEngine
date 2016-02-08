package at.korti.katlonengine.client.render;

import at.korti.katlonengine.client.display.Camera;
import at.korti.katlonengine.client.model.Model;
import at.korti.katlonengine.client.model.TexturedModel;
import at.korti.katlonengine.client.model.TexturedVAOModel;
import at.korti.katlonengine.client.model.VAOModel;
import at.korti.katlonengine.client.resources.Icon;
import at.korti.katlonengine.client.shader.MasterShader;
import at.korti.katlonengine.components.Light;
import at.korti.katlonengine.entity.Entity;
import at.korti.katlonengine.util.GroupingList;
import at.korti.katlonengine.util.helper.MatrixHelper;
import at.korti.katlonengine.util.helper.OpenGLHelper;
import at.korti.katlonengine.util.matrix.Matrix4f;
import at.korti.katlonengine.util.vector.Vector3f;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Korti on 08.01.2016.
 */
public class MasterRenderer {

    private static MasterRenderer instance;
    public Camera camera;
    public Light light;
    private List<Entity> entities;
    private List<GroupingList<Model, Entity>> groupedEntites;
    private Map<Model, VAOModel> vaoModels;
    private boolean entitiesChange;
    private MasterShader shader;
    private Matrix4f projectionMatrix;

    private MasterRenderer() {
        entities = new LinkedList<>();
        groupedEntites = new LinkedList<>();
        vaoModels = new HashMap<>();
        entitiesChange = true;
    }

    public static MasterRenderer instance() {
        if (instance == null) {
            instance = new MasterRenderer();
        }
        return instance;
    }

    public static void addEntity(Entity entity) {
        instance().entities.add(entity);
        instance().entitiesChange = true;
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

    private void prepare() {
        glEnable(GL_DEPTH_TEST);
        OpenGLHelper.clearFramebuffer();
        OpenGLHelper.clearColor();

        groupEntites();
    }

    private void groupEntites() {
        if (entitiesChange) {
            groupedEntites.clear();
            for (Entity entity : entities) {
                if (hasGrouped(entity.model)) {
                    continue;
                }
                groupedEntites.add(GroupingList.groupBy(entity.getModel(), entities, m -> m.getModel()));
            }
            createVAOModels();
            entitiesChange = false;
        }
    }

    private boolean hasGrouped(Model model) {
        for (GroupingList grouping : groupedEntites) {
            if (grouping.getKey().equals(model)) {
                return true;
            }
        }
        return false;
    }

    private void createVAOModels() {
        for (GroupingList<Model, Entity> groupedList : groupedEntites) {
            Model model = groupedList.getKey();
            if (!hasVAOCreated(model)) {
                if (model instanceof TexturedModel) {
                    vaoModels.put(model, new TexturedVAOModel((TexturedModel) model));
                } else {
                    vaoModels.put(model, new VAOModel(model));
                }
            }
        }
    }

    private boolean hasVAOCreated(Model model) {
        for (Map.Entry<Model, VAOModel> entry : vaoModels.entrySet()) {
            if (entry.getValue().getModel().equals(model)) {
                return true;
            }
        }
        return false;
    }

    public void renderAll() {
        prepare();
        for (GroupingList<Model, Entity> groupedList : groupedEntites) {
            VAOModel model = vaoModels.get(groupedList.getKey());
            shader.start();
            model.bindVAO();
            model.enableVertexAttribArray();
            shader.loadViewMatrix(MatrixHelper.createViewMatrix(camera));
            if (model instanceof TexturedVAOModel) {
                TexturedModel texturedModel = (TexturedModel) model.getModel();
                Icon texture = texturedModel.getTexture();
                texture.enable();
                texture.loadTexture();
            }
            for (Entity entity : groupedList.getValues()) {
                shader.loadTransformationMatrix(entity.getTransformation());
                glDrawElements(GL_TRIANGLES, model.getModel().getFaces().size() * 3, GL_UNSIGNED_INT, 0);
            }
            glDisable(GL_TEXTURE_2D);
            model.disableVertexAttribArray();
            model.unbindVAO();
            shader.stop();
        }
    }

    public void cleanUp() {
        shader.cleanUp();
        vaoModels.forEach((model, vaoModel) -> vaoModel.cleanUp());
    }

}
