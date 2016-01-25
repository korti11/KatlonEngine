package at.korti.katlonengine.client.model;

import at.korti.katlonengine.util.vector.Vector3f;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Korti on 07.01.2016.
 */
public class Model {

    protected final List<Vector3f> vertices;
    protected final List<Vector3f> normals;
    protected final List<Face> faces;
    protected final Map<String, Material> materials;

    public Model() {
        vertices = new LinkedList<>();
        normals = new LinkedList<>();
        faces = new LinkedList<>();
        materials = new HashMap<>();
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

    public Map<String, Material> getMaterials() {
        return materials;
    }

    public static class Material{

        private Vector3f ambientColor;
        private Vector3f diffuseColor;
        private Vector3f specularColor;
        private float specularWeight;

        public Material(Vector3f ambientColor, Vector3f diffuseColor, Vector3f specularColor, float specularWeight) {
            this.ambientColor = ambientColor;
            this.diffuseColor = diffuseColor;
            this.specularColor = specularColor;
            this.specularWeight = specularWeight;
        }

        public Vector3f getAmbientColor() {
            return ambientColor;
        }

        public Vector3f getDiffuseColor() {
            return diffuseColor;
        }

        public Vector3f getSpecularColor() {
            return specularColor;
        }

        public float getSpecularWeight() {
            return specularWeight;
        }
    }
}
