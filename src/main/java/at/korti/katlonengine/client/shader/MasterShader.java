package at.korti.katlonengine.client.shader;

/**
 * Created by Korti on 10.01.2016.
 */
public class MasterShader extends Shader {

    private static final String VERTEX_FILE = "shader/masterShader.vs";
    private static final String FRAGMENT_FILE = "shader/masterShader.fs";

    public MasterShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {

    }

    @Override
    protected void getAllUniformLocations() {

    }
}
