package at.korti.katlonengine.client.model;

import at.korti.katlonengine.util.helper.BufferHelper;
import at.korti.katlonengine.util.vector.Vector3f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by Korti on 08.01.2016.
 */
public class VAOModel {

    public static final int vertexAttribute = 0;
    public static final int normalAttribute = 1;
    public static final int textureAttribute = 2;
    private final int vaoID;
    protected Model model;
    private int vboVertexHandler;
    private int vboNormalHandler;
    private int vboIndicesHandler;

    public VAOModel(Model model) {
        this.model = model;
        vaoID = glGenVertexArrays();
        setupModel();
    }

    protected void setupModel() {
        bindVAO();
        //Create the vbo handlers
        vboVertexHandler = glGenBuffers();
        vboNormalHandler = glGenBuffers();
        vboIndicesHandler = glGenBuffers();

        int[] indices = new int[model.getFaces().size() * 3];
        float[] vertices = new float[model.getVertices().size() * 3];
        float[] normals = new float[model.getVertices().size() * 3];

        int count = 0;
        for (Face face : model.getFaces()) {
            for (int i = 0; i < 3; i++) {
                int vertexPointer = face.getVertexIndices()[i];
                indices[count] = vertexPointer;
                int normalPointer = face.getNormalIndices()[i];
                Vector3f normalVector = model.getNormals().get(normalPointer);
                normals[vertexPointer * 3] = normalVector.x;
                normals[vertexPointer * 3 + 1] = normalVector.y;
                normals[vertexPointer * 3 + 2] = normalVector.z;
                count++;
            }
        }
        count = 0;

        for (Vector3f vector : model.getVertices()) {
            vertices[count++] = vector.x;
            vertices[count++] = vector.y;
            vertices[count++] = vector.z;
        }

        IntBuffer indicesBuffer = BufferHelper.store(indices);
        FloatBuffer verticesBuffer = BufferHelper.store(vertices);
        FloatBuffer normalsBuffer = BufferHelper.store(normals);

        //Bind indices
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboIndicesHandler);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandler);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(vertexAttribute, 3, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, vboNormalHandler);
        glBufferData(GL_ARRAY_BUFFER, normalsBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(normalAttribute, 3, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        unbindVAO();
    }

    public int getVboVertexHandler() {
        return vboVertexHandler;
    }

    public int getVboNormalHandler() {
        return vboNormalHandler;
    }

    public Model getModel() {
        return model;
    }

    public void bindVAO() {
        glBindVertexArray(vaoID);
    }

    public void unbindVAO() {
        glBindVertexArray(0);
    }

    public void enableVertexAttribArray() {
        glEnableVertexAttribArray(VAOModel.vertexAttribute);
        glEnableVertexAttribArray(VAOModel.normalAttribute);
    }

    public void disableVertexAttribArray() {
        glDisableVertexAttribArray(VAOModel.vertexAttribute);
        glDisableVertexAttribArray(VAOModel.normalAttribute);
    }

    public void cleanUp() {
        glDeleteVertexArrays(vaoID);
        glDeleteBuffers(vboVertexHandler);
        glDeleteBuffers(vboIndicesHandler);
        glDeleteBuffers(vboNormalHandler);
    }
}
