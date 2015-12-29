package at.korti.katlonengine.client;

import at.korti.katlonengine.config.EngineSettings;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by Korti on 29.12.2015.
 */
public class DisplayManager {

    private static DisplayManager instance;

    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    private long window;

    private DisplayManager(){

    }

    public static DisplayManager instance() {
        if (instance == null) {
            instance = new DisplayManager();
        }
        return instance;
    }

    public void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if(glfwInit() != GLFW_TRUE) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure our window
        glfwDefaultWindowHints(); //optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, EngineSettings.resizeable ? GLFW_TRUE : GLFW_FALSE); // the window will be resizeable, if the resizeable setting is true

        // Create the window
        window = glfwCreateWindow(EngineSettings.width, EngineSettings.width, EngineSettings.displayTitle, NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        //Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (EngineSettings.keyCloseActive && key == EngineSettings.CLOSE_KEY && action == GLFW_RELEASE) {
                    glfwSetWindowShouldClose(window, GLFW_TRUE);
                }
            }
        });

        // Get the resolution of the primary monitor
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                window,
                (vidMode.width() - EngineSettings.width) / 2,
                (vidMode.height() - EngineSettings.height) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        if(EngineSettings.vSync) {
            glfwSwapInterval(1);
        }

        // Make the window visible
        glfwShowWindow(window);
    }

    public void swapColorBuffers(){
        glfwSwapBuffers(window);
    }

    public void pollEvents() {
        glfwPollEvents();
    }

    public boolean isCloseKeyPressed(){
        return EngineSettings.keyCloseActive && glfwWindowShouldClose(window) == GLFW_FALSE;
    }

    public void close(){
        glfwDestroyWindow(window);
        keyCallback.release();
    }

    public void terminate(){
        glfwTerminate();
        errorCallback.release();
    }

}
