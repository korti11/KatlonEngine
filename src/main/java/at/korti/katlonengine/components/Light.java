package at.korti.katlonengine.components;

import at.korti.katlonengine.util.vector.Vector3f;

/**
 * Created by Korti on 02.02.2016.
 */
public class Light {

    private Vector3f positon;
    private Vector3f color;

    public Light(Vector3f positon, Vector3f color) {
        this.positon = positon;
        this.color = color;
    }

    public Vector3f getPositon() {
        return positon;
    }

    public void setPositon(Vector3f positon) {
        this.positon = positon;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }
}
