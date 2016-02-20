package at.korti.katlonengine.components;

import at.korti.katlonengine.KatlonEngine;
import at.korti.katlonengine.entity.Entity;
import at.korti.katlonengine.util.helper.PhysicsHelper;

/**
 * Created by Korti on 12.02.2016.
 */
public class RigidbodyComponent implements IComponent {

    public float mass;
    public float drag;
    public float angularDrag;
    public boolean useGravity;

    private Entity entity;
    private float gravityTime;

    public RigidbodyComponent() {
        this.mass = 1f;
        this.drag = 0f;
        this.angularDrag = 0f;
        this.useGravity = true;
    }

    public RigidbodyComponent(float mass, float drag, float angularDrag, boolean useGravity) {
        this.mass = mass;
        this.drag = drag;
        this.angularDrag = angularDrag;
        this.useGravity = useGravity;
    }

    //region Getter and Setter
    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getDrag() {
        return drag;
    }

    public void setDrag(float drag) {
        this.drag = drag;
    }

    public float getAngularDrag() {
        return angularDrag;
    }

    public void setAngularDrag(float angularDrag) {
        this.angularDrag = angularDrag;
    }

    public boolean isUseGravity() {
        return useGravity;
    }

    public void setUseGravity(boolean useGravity) {
        this.useGravity = useGravity;
    }
    //endregion

    @Override
    public void init(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void update() {
        if (useGravity) {
            entity.velocity.y -= PhysicsHelper.calculateFallVelocity(mass, drag, gravityTime, entity.velocity.y);
            gravityTime += KatlonEngine.deltaTime;
        }
    }
}