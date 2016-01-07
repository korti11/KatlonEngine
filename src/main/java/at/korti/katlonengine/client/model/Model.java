package at.korti.katlonengine.client.model;

import at.korti.katlonengine.util.vector.Vector3f;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Korti on 07.01.2016.
 */
public class Model {

    private final List<Vector3f> vertices;
    private final List<Vector3f> normals;
    private final List<Face> faces;

    public Model() {
        vertices = new LinkedList<>();
        normals = new LinkedList<>();
        faces = new LinkedList<>();
    }

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
