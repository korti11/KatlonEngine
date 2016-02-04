package at.korti.katlonengine.client.model;

import at.korti.katlonengine.util.vector.Vector3f;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Korti on 07.01.2016.
 */
public class Model {

    //List of all vertices of the model.
    protected final List<Vector3f> vertices;
    //List of all normals of the model.
    protected final List<Vector3f> normals;
    //List of all faces of the model.
    protected final List<Face> faces;

    public Model() {
        vertices = new LinkedList<>();
        normals = new LinkedList<>();
        faces = new LinkedList<>();
    }

    /**
     * @return If the model has normals.
     */
    public boolean hasNormals() {
        return normals.size() > 0;
    }

    public List<Vector3f> getVertices() {
        return vertices;
    }

    public List<Vector3f> getNormals() {
        return normals;
    }

    public List<Face> getFaces() {
        return faces;
    }

}
