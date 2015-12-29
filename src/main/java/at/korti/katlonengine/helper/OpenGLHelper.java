package at.korti.katlonengine.helper;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Korti on 29.12.2015.
 */
public class OpenGLHelper {

    //Default clear color (Black)
    private static float r,g,b,a = 0.0f;

    /**
     * Set the clear color.
     * Color: Black
     */
    public static void clearColor() {
        glClearColor(r, g, b, a);
    }

    /**
     * Set the clear color.
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @param a Alpha value
     */
    public static void clearColor(float r, float g, float b, float a) {
        glClearColor(r, g, b, a);
    }

    /**
     * Clear the framebuffer
     */
    public static void clearFramebuffer() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
