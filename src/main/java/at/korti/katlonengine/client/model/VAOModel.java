package at.korti.katlonengine.client.model;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by Korti on 08.01.2016.
 */
public class VAOModel {

    public static final int vertexAttribute = 0;
    public static final int normalAttribute = 1;

    private int vboVertexHandler;
    private int vboNormalHandler;
    private int vboIndicesHandler;
    private final int vaoID;
    private Model model;

    public VAOModel(Model model) {
        this.model = model;
        vaoID = glGenVertexArrays();
        setupModel();
    }

    private void setupModel() {
        bindVAO();
        //Create the vbo handlers
        vboVertexHandler = glGenBuffers();
        vboNormalHandler = glGenBuffers();
        vboIndicesHandler = glGenBuffers();

        IntBuffer indices = BufferUtils.createIntBuffer(model.getFaces().size() * 3);
        FloatBuffer vertices = BufferUtils.createFloatBuffer(model.getFaces().size() * 9);
        FloatBuffer normals = BufferUtils.createFloatBuffer(model.getFaces().size() * 9);
        //TODO: Performence issue. Better method to load the vertices and normals in the float buffers.
        for (Face face : model.getFaces()) {
            indices.put(face.getVertexIndices()[0]);
            indices.put(face.getVertexIndices()[1]);
            indices.put(face.getVertexIndices()[2]);
            vertices.put(model.getVertices().get(face.getVertexIndices()[0] - 1).toFloatArray());
            vertices.put(model.getVertices().get(face.getVertexIndices()[1] - 1).toFloatArray());
            vertices.put(model.getVertices().get(face.getVertexIndices()[2] - 1).toFloatArray());
            normals.put(model.getNormals().get(face.getNormalIndices()[0] - 1).toFloatArray());
            normals.put(model.getNormals().get(face.getNormalIndices()[1] - 1).toFloatArray());
            normals.put(model.getNormals().get(face.getNormalIndices()[2] - 1).toFloatArray());
        }

        indices.flip();
        vertices.flip();
        normals.flip();

        //Bind indices
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboIndicesHandler);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandler);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glVertexAttribPointer(vertexAttribute, 3, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, vboNormalHandler);
        glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
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
//        glEnableVertexAttribArray(VAOModel.normalAttribute);
    }

    public void disableVertexAttribArray() {
        glDisableVertexAttribArray(VAOModel.vertexAttribute);
//        glDisableVertexAttribArray(VAOModel.normalAttribute);
    }

    public void cleanUp() {
        glDeleteVertexArrays(vaoID);
        glDeleteBuffers(vboVertexHandler);
        glDeleteBuffers(vboVertexHandler);
    }
}
