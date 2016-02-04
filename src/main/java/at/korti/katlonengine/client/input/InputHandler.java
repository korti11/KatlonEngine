package at.korti.katlonengine.client.input;

import at.korti.katlonengine.client.input.event.CursorPosEvent;
import at.korti.katlonengine.client.input.event.KeyInputEvent;
import at.korti.katlonengine.client.input.event.MouseButtonInputEvent;
import at.korti.katlonengine.client.input.event.ScrollInputEvent;
import at.korti.katlonengine.config.EngineSettings;
import at.korti.katlonengine.event.handler.SubscribeEvent;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import static at.korti.katlonengine.KatlonEngine.EVENT_BUS;
import static org.lwjgl.glfw.GLFW.*;
/**
 * Created by Korti on 06.01.2016.
 */
public class InputHandler {

    private GLFWKeyCallback keyCallback;
    private GLFWMouseButtonCallback mouseButtonCallback;
    private GLFWCursorPosCallback cursorPosCallback;
    private GLFWScrollCallback scrollCallback;

    private long window;

    public InputHandler(long window) {
        this.window = window;
    }

    /**
     * Init all inputs.
     */
    public void init() {

        //Register input events
        EVENT_BUS.registerEvent(KeyInputEvent.class);
        EVENT_BUS.registerEvent(MouseButtonInputEvent.class);
        EVENT_BUS.registerEvent(CursorPosEvent.class);
        EVENT_BUS.registerEvent(ScrollInputEvent.class);

        //Set GLFW input callbacks
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                EVENT_BUS.fireEvent(new KeyInputEvent(window, key, scancode, action, mods));
            }
        });

        glfwSetMouseButtonCallback(window, mouseButtonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                EVENT_BUS.fireEvent(new MouseButtonInputEvent(window, button, action, mods));
            }
        });

        glfwSetCursorPosCallback(window, cursorPosCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                EVENT_BUS.fireEvent(new CursorPosEvent(window, xpos, ypos));
            }
        });

        glfwSetScrollCallback(window, scrollCallback = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                EVENT_BUS.fireEvent(new ScrollInputEvent(window, xoffset, yoffset));
            }
        });

        EVENT_BUS.register(this);
    }

    public void release() {
        keyCallback.release();
        mouseButtonCallback.release();
        cursorPosCallback.release();
        scrollCallback.release();
    }

    @SubscribeEvent
    public void handleKeyInput(KeyInputEvent event) {
        if (EngineSettings.keyCloseActive && event.getKey() == EngineSettings.CLOSE_KEY) {
            glfwSetWindowShouldClose(window, GLFW_TRUE);
        }
    }
}
