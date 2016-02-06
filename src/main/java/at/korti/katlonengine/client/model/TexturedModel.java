package at.korti.katlonengine.client.model;

import at.korti.katlonengine.client.resources.Icon;
import at.korti.katlonengine.util.vector.Vector2f;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Korti on 04.02.2016.
 */
public class TexturedModel extends Model {

    //List of all texture vertices of the model
    protected List<Vector2f> textureVertices;
    protected Icon texture;

    public TexturedModel() {
        super();
        textureVertices = new LinkedList<>();
    }

    public TexturedModel(Icon texture) {
        this();
        this.texture = texture;
    }

    public boolean hasTexture() {
        return textureVertices.size() != 0;
    }

    public List<Vector2f> getTextureVertices() {
        return textureVertices;
    }

    public Icon getTexture() {
        return texture;
    }

    public void setTexture(Icon texture) {
        this.texture = texture;
    }
}
