package at.korti.katlonengine.client.model;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * Created by Korti on 08.01.2016.
 */
public class VBOModel {

    private int vboVertexHandler;
    private int vboNormalHandler;
    private Model model;

    public VBOModel(Model model) {
        this.model = model;
        setupModel();
    }

    private void setupModel() {
        //Create the vbo handlers
        vboVertexHandler = glGenBuffers();
        vboNormalHandler = glGenBuffers();

        FloatBuffer vertices = BufferUtils.createFloatBuffer(model.getFaces().size() * 9);
        FloatBuffer normals = BufferUtils.createFloatBuffer(model.getFaces().size() * 9);

        for (Face face : model.getFaces()) {
            vertices.put(model.getVertices().get(face.getVertexIndices()[0] - 1).toFloatArray());
            vertices.put(model.getVertices().get(face.getVertexIndices()[1] - 1).toFloatArray());
            vertices.put(model.getVertices().get(face.getVertexIndices()[2] - 1).toFloatArray());
            normals.put(model.getNormals().get(face.getNormalIndices()[0] - 1).toFloatArray());
            normals.put(model.getNormals().get(face.getNormalIndices()[1] - 1).toFloatArray());
            normals.put(model.getNormals().get(face.getNormalIndices()[2] - 1).toFloatArray());
        }

        vertices.flip();
        normals.flip();

        glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandler);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glVertexPointer(3, GL_FLOAT, 0, 0L);
        glBindBuffer(GL_ARRAY_BUFFER, vboNormalHandler);
        glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
        glNormalPointer(GL_FLOAT, 0, 0L);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public int getVboVertexHandler() {
        return vboVertexHandler;
    }

    public int getVboNormalHandler() {
        return vboNormalHandler;
    }

    public void cleanUp() {
        glDeleteBuffers(vboVertexHandler);
        glDeleteBuffers(vboVertexHandler);
    }
}
