package at.korti.katlonengine.entity;

import at.korti.katlonengine.client.model.Model;
import at.korti.katlonengine.client.model.ModelRegistry;
import at.korti.katlonengine.components.IComponent;
import at.korti.katlonengine.util.helper.MatrixHelper;
import at.korti.katlonengine.util.matrix.Matrix4f;
import at.korti.katlonengine.util.vector.Vector3f;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Korti on 07.02.2016.
 */
public class Entity {

    /**
     * World position of the entity
     */
    public Vector3f position;
    /**
     * Rotation of the entity
     */
    public Vector3f rotation;
    /**
     * The scale of the entity
     */
    public float scale;
    /**
     * The model that is shown in the world
     */
    public Model model;
    public List<IComponent> components;

    private Entity() {
        components = new LinkedList<>();
    }

    /**
     * Constructor.
     *
     * @param model    The model that is shown
     * @param position Position in the world
     * @param rotation Rotation of the entity
     * @param scale    The scale of the entity
     */
    public Entity(Model model, Vector3f position, Vector3f rotation, float scale) {
        this();
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    /**
     * Constructor.
     *
     * @param model The model that is shown
     * @param x     The position on the x axis in the world
     * @param y     The position on the y axis in the world
     * @param z     The position on the z axis in the world
     * @param rx    The rotation on the x axis
     * @param ry    The rotation on the y axis
     * @param rz    The rotation on the z axis
     * @param scale The scale of the entity
     */
    public Entity(Model model, float x, float y, float z, float rx, float ry, float rz, float scale) {
        this();
        this.model = model;
        this.position = new Vector3f(x, y, z);
        this.rotation = new Vector3f(rx, ry, rz);
        this.scale = scale;
    }

    /**
     * Constructor.
     *
     * @param modelId  The model id in the model registry
     * @param position Position in the world
     * @param rotation Rotation of the entity
     * @param scale    The scale of the entity
     */
    public Entity(String modelId, Vector3f position, Vector3f rotation, float scale) {
        this();
        this.model = ModelRegistry.getModel(modelId);
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    /**
     * Constructor.
     *
     * @param modelId The model id in the model registry
     * @param x       The position on the x axis in the world
     * @param y       The position on the y axis in the world
     * @param z       The position on the z axis in the world
     * @param rx      The rotation on the x axis
     * @param ry      The rotation on the y axis
     * @param rz      The rotation on the z axis
     * @param scale   The scale of the entity
     */
    public Entity(String modelId, float x, float y, float z, float rx, float ry, float rz, float scale) {
        this();
        this.model = ModelRegistry.getModel(modelId);
        this.position = new Vector3f(x, y, z);
        this.rotation = new Vector3f(rx, ry, rz);
        this.scale = scale;
    }

    //region Getter and Setter
    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    //endregion

    public void addComponent(IComponent component) {
        this.components.add(component);
    }

    public void update() {
        components.forEach(component -> component.update(this));
    }

    public Matrix4f getTransformation() {
        return MatrixHelper.createTransformationMatrix(position, rotation.x, rotation.y, rotation.z, scale);
    }
}
