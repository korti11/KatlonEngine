package at.korti.katlonengine.config;

import org.lwjgl.Version;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Korti on 29.12.2015.
 */
public class EngineSettings {

    //FPS settings
    public static boolean activeFpsCap = true;
    public static int fpsCap = 120;

    //Window settings
    public static int width = 1280;
    public static int height = 720;
    public static String displayTitle = "Katlon Engine";
    public static boolean resizeable = true;
    public static boolean vSync = true;
    public static boolean keyCloseActive = false;
    public static float fieldOfView = 70;

    //LWJGL
    public static final String lwjglVersion = Version.getVersion();

    //Keys
    public static int CLOSE_KEY = GLFW_KEY_ESCAPE;

}
