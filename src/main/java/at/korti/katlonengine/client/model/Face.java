package at.korti.katlonengine.client.model;

/**
 * Created by Korti on 07.01.2016.
 */
public class Face {

    private final int[] vertexIndices = {-1, -1, -1};
    private final int[] normalIndices = {-1, -1, -1};

    public Face(int[] vertexIndices) {
        this.vertexIndices[0] = vertexIndices[0];
        this.vertexIndices[1] = vertexIndices[1];
        this.vertexIndices[2] = vertexIndices[2];
    }

    public Face(int[] vertexIndices, int[] normalIndices) {
        this(vertexIndices);
        this.normalIndices[0] = normalIndices[0];
        this.normalIndices[1] = normalIndices[1];
        this.normalIndices[2] = normalIndices[2];
    }
}
