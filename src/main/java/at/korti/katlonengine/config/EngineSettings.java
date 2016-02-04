package at.korti.katlonengine.config;

import org.lwjgl.Version;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

/**
 * Created by Korti on 29.12.2015.
 */
public class EngineSettings {

    //LWJGL
    public static final String lwjglVersion = Version.getVersion();     //The used LWJGL version.
    //Window settings
    public static int width = 1280; //The start width of the window.
    public static int height = 720; //The start height of the window.
    public static String displayTitle = "Katlon Engine";    //The title of the window.
    public static boolean resizeable = true;    //Is the window resizeable.
    public static boolean vSync = true;     //Is vertical synchronisation on.
    public static boolean keyCloseActive = false;   //Can the window closed by a key.
    public static float fieldOfView = 70;       //The field of view of the camera.
    //Keys
    public static int CLOSE_KEY = GLFW_KEY_ESCAPE;      //The key that can close the window.

}
