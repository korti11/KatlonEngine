package at.korti.katlonengine.scene;

import at.korti.katlonengine.client.shader.Shader;
import at.korti.katlonengine.entity.Entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Korti on 08.02.2016.
 */
public abstract class AbstractScene {

    protected List<Entity> entities;
    protected boolean entitiesChanged;
    protected Shader shader;
    private boolean isInit = false;

    public AbstractScene(Shader shader) {
        entities = new LinkedList<>();
        this.shader = shader;
        this.entitiesChanged = true;
    }

    public abstract void init();

    public void postInit() {
        isInit = true;
    }

    public abstract void render();

    public abstract void update();

    public abstract void reload();

    public void addEntity(Entity entity) {
        entities.add(entity);
        this.entitiesChanged = true;
    }

    public boolean isInit() {
        return isInit;
    }

    public void cleanUp() {
        this.shader.cleanUp();
    }
}
