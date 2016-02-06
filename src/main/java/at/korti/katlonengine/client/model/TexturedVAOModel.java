package at.korti.katlonengine.client.model;

import at.korti.katlonengine.util.helper.BufferHelper;
import at.korti.katlonengine.util.vector.Vector2f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by Korti on 04.02.2016.
 */
public class TexturedVAOModel extends VAOModel {

    private int vboTextureHandler;

    public TexturedVAOModel(TexturedModel model) {
        super(model);
    }

    @Override
    protected void setupModel() {
        super.setupModel();
        bindVAO();
        vboTextureHandler = glGenBuffers();
        TexturedModel tempModel = (TexturedModel) model;

        float[] textureVertices = new float[model.getVertices().size() * 2];
        for (Face face : model.getFaces()) {
            for (int i = 0; i < 3; i++) {
                int vertexPointer = face.getVertexIndices()[i];
                int texturePointer = face.getTextureIndices()[i];
                Vector2f textureVector = tempModel.getTextureVertices().get(texturePointer);
                textureVertices[vertexPointer * 2] = textureVector.x;
                textureVertices[vertexPointer * 2 + 1] = textureVector.y;
            }
        }

        FloatBuffer textureBuffer = BufferHelper.store(textureVertices);

        glBindBuffer(GL_ARRAY_BUFFER, vboTextureHandler);
        glBufferData(GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(textureAttribute, 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        unbindVAO();
    }

    @Override
    public void enableVertexAttribArray() {
        super.enableVertexAttribArray();
        glEnableVertexAttribArray(textureAttribute);
    }

    @Override
    public void disableVertexAttribArray() {
        super.disableVertexAttribArray();
        glDisableVertexAttribArray(textureAttribute);
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
        glDeleteBuffers(vboTextureHandler);
    }
}
