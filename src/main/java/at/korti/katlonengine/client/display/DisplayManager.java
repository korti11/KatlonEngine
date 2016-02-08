package at.korti.katlonengine.client.display;

import at.korti.katlonengine.KatlonEngine;
import at.korti.katlonengine.client.input.InputHandler;
import at.korti.katlonengine.config.EngineSettings;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by Korti on 29.12.2015.
 */
public final class DisplayManager {

    private static DisplayManager instance;

    private GLFWErrorCallback errorCallback;

    private Logger logger = KatlonEngine.logger;
    private InputHandler inputHandler;

    private long window;

    private DisplayManager(){

    }

    public static DisplayManager instance() {
        if (instance == null) {
            instance = new DisplayManager();
        }
        return instance;
    }

    /**
     * Init a new window.
     */
    public void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if(glfwInit() != GLFW_TRUE) {
            logger.error("Unable to initialize GLFW");
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure our window
        glfwDefaultWindowHints(); //optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, EngineSettings.resizeable ? GLFW_TRUE : GLFW_FALSE); // the window will be resizeable, if the resizeable setting is true

        // Create the window
        window = glfwCreateWindow(EngineSettings.width, EngineSettings.height, EngineSettings.displayTitle, NULL, NULL);
        if (window == NULL) {
            logger.error("Failed to create the GLFW window");
            throw new RuntimeException("Failed to create the GLFW window");
        }
        inputHandler = new InputHandler(window);
        inputHandler.init();

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

    /**
     * @return Get the width of the window.
     */
    public int getWidth() {
        IntBuffer buffer = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(window, buffer, null);
        return buffer.get();
    }

    /**
     * @return Get the height of the window.
     */
    public int getHeight() {
        IntBuffer buffer = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(window, null, buffer);
        return buffer.get();
    }

    public void swapColorBuffers(){
        glfwSwapBuffers(window);
    }

    public void pollEvents() {
        glfwPollEvents();
    }

    /**
     * @return Check if the window should be closed.
     */
    public boolean shouldWindowClose(){
        return glfwWindowShouldClose(window) == GLFW_FALSE;
    }

    public void close(){
        glfwDestroyWindow(window);
        inputHandler.release();
    }

    public void terminate(){
        glfwTerminate();
        errorCallback.release();
    }

}
