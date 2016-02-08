package at.korti.katlonengine.scene;

import at.korti.katlonengine.client.model.VAOModel;
import at.korti.katlonengine.client.shader.Shader;
import at.korti.katlonengine.components.Light;
import at.korti.katlonengine.util.matrix.Matrix4f;

/**
 * Created by Korti on 08.02.2016.
 */
public class SceneShader extends Shader {

    private static final String VERTEX_FILE = "shader/sceneShader.vs.glsl";
    private static final String FRAGMENT_FILE = "shader/sceneShader.fs.glsl";

    private int transformationMatrix;
    private int projectionMatrix;
    private int viewMatrix;
    private int lightPosition;
    private int lightColor;

    public SceneShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        bindAttribute(VAOModel.vertexAttribute, "position");
        bindAttribute(VAOModel.normalAttribute, "normals");
        bindAttribute(VAOModel.textureAttribute, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        transformationMatrix = getUniformLocation("transformationMatrix");
        projectionMatrix = getUniformLocation("projectionMatrix");
        viewMatrix = getUniformLocation("viewMatrix");
        lightPosition = getUniformLocation("lightPosition");
        lightColor = getUniformLocation("lightColor");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        loadMatrix4f(transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        loadMatrix4f(projectionMatrix, matrix);
    }

    public void loadViewMatrix(Matrix4f matrix) {
        loadMatrix4f(viewMatrix, matrix);
    }

    public void loadLight(Light light) {
        loadVector(lightPosition, light.getPositon());
        loadVector(lightColor, light.getColor());
    }
}
