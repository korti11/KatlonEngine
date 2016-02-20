package at.korti.katlonengine.scene;

import at.korti.katlonengine.client.display.Camera;
import at.korti.katlonengine.client.model.Model;
import at.korti.katlonengine.client.model.TexturedModel;
import at.korti.katlonengine.client.model.TexturedVAOModel;
import at.korti.katlonengine.client.model.VAOModel;
import at.korti.katlonengine.client.resources.Icon;
import at.korti.katlonengine.components.Light;
import at.korti.katlonengine.entity.Entity;
import at.korti.katlonengine.util.GroupingList;
import at.korti.katlonengine.util.helper.MatrixHelper;
import at.korti.katlonengine.util.helper.OpenGLHelper;
import at.korti.katlonengine.util.vector.Vector3f;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Korti on 08.02.2016.
 */
public class Scene extends AbstractScene {

    private Light light;
    private Camera camera;

    private List<GroupingList<Model, Entity>> groupEntities;
    private Map<Model, VAOModel> vaoModels;

    public Scene() {
        super(new SceneShader());
        this.light = new Light(new Vector3f(0f, 10f, 20f), new Vector3f(1f, 1f, 1f));
        this.camera = new Camera();

        this.groupEntities = new LinkedList<>();
        this.vaoModels = new HashMap<>();
    }

    //region Getter and Setter
    public void setLightPos(float x, float y, float z) {
        this.light.setPositon(new Vector3f(x, y, z));
    }

    public void setLightColor(float r, float g, float b) {
        this.light.setColor(new Vector3f(r, g, b));
    }

    private SceneShader getShader() {
        return (SceneShader) shader;
    }
    //endregion

    @Override
    public void init() {
        shader.start();
        getShader().loadLight(light);
        getShader().loadProjectionMatrix(MatrixHelper.projectionMatrix());
        shader.stop();
        entities.forEach(Entity::init);
    }

    @Override
    public void render() {
        //Prepare
        glEnable(GL_DEPTH_TEST);
        OpenGLHelper.clearFramebuffer();
        OpenGLHelper.clearColor();

        shader.start();
        groupEntities.forEach((grouping) -> {
            VAOModel model = vaoModels.get(grouping.getKey());
            model.bindVAO();
            model.enableVertexAttribArray();

            if (model instanceof TexturedVAOModel) {
                TexturedModel texturedModel = (TexturedModel) model.getModel();
                Icon texture = texturedModel.getTexture();
                Icon.enableTexture2D();
                texture.loadTexture();
            }

            grouping.getValues().forEach((entity) -> {
                getShader().loadTransformationMatrix(entity.getTransformation());
                glDrawElements(GL_TRIANGLES, model.getModel().getFaces().size() * 3, GL_UNSIGNED_INT, 0);
            });

            Icon.disableTexture2D();
            model.disableVertexAttribArray();
            model.unbindVAO();
        });
        shader.stop();
    }

    @Override
    public void update() {
        shader.start();
        getShader().loadLight(light);
        getShader().loadViewMatrix(MatrixHelper.createViewMatrix(camera));
        shader.stop();

        if (entitiesChanged) {
            groupEntities();
            createVAOModels();
            entitiesChanged = false;
        }

        entities.forEach(Entity::update);
    }

    @Override
    public void reload() {
        shader = new SceneShader();
        shader.start();
        getShader().loadLight(light);
        getShader().loadProjectionMatrix(MatrixHelper.projectionMatrix());
        shader.stop();

        groupEntities();
        createVAOModels();
    }

    //region Entity/Model management
    private void groupEntities() {
        groupEntities.clear();
        for (Entity entity : entities) {
            if (hasGrouped(entity.getModel())) {
                continue;
            }
            groupEntities.add(GroupingList.groupBy(entity.getModel(), entities, m -> m.getModel()));
        }
    }

    private boolean hasGrouped(Model model) {
        for (GroupingList grouping : groupEntities) {
            if (grouping.getKey().equals(model)) {
                return true;
            }
        }
        return false;
    }

    private void createVAOModels() {
        for (GroupingList<Model, Entity> grouping : groupEntities) {
            Model model = grouping.getKey();
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
            if (entry.getKey().equals(model)) {
                return true;
            }
        }
        return false;
    }
    //endregion

    @Override
    public void cleanUp() {
        super.cleanUp();
        vaoModels.values().forEach(VAOModel::cleanUp);
        vaoModels.clear();
    }
}
