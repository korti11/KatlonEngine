package at.korti.katlonengine.client.resources;

import at.korti.katlonengine.client.resources.exception.IconException;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

/**
 * Created by Korti on 04.02.2016.
 */
public final class Icon {

    private final ByteBuffer image;

    private final int width;
    private final int height;
    private final int components;
    private final int textureID;

    public Icon(ByteBuffer imageBuffer) {
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer components = BufferUtils.createIntBuffer(1);

        if (stbi_info_from_memory(imageBuffer, width, height, components) == 0) {
            throw new IconException("Fail to read image information: " + stbi_failure_reason());
        }

        image = stbi_load_from_memory(imageBuffer, width, height, components, 0);
        if (image == null) {
            throw new IconException("Fail to load image: " + stbi_failure_reason());
        }

        this.width = width.get(0);
        this.height = height.get(0);
        this.components = components.get(0);
        this.textureID = glGenTextures();
    }

    public static void enableTexture2D() {
        glEnable(GL_TEXTURE_2D);
    }

    public static void disableTexture2D() {
        glDisable(GL_TEXTURE_2D);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getComponents() {
        return components;
    }

    public int getTextureID() {
        return textureID;
    }

    public void loadTexture() {
        glBindTexture(GL_TEXTURE_2D, textureID);
        if (components == 3) {
            if ((width & 3) != 0) {
                glPixelStoref(GL_UNPACK_ALIGNMENT, 2 - (width & 1));
            }
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, image);
        } else {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        }
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    }
}
