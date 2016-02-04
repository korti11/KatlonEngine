package at.korti.katlonengine.client.display;

import at.korti.katlonengine.KatlonEngine;
import at.korti.katlonengine.client.input.event.KeyInputEvent;
import at.korti.katlonengine.event.handler.SubscribeEvent;
import at.korti.katlonengine.util.vector.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
/**
 * Created by Korti on 12.01.2016.
 */
public class Camera {

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch = 0;
    private float yaw;
    private float moveSpeed = 0.02f;

    public Camera() {
        KatlonEngine.EVENT_BUS.register(this);
    }

    /**
     * Handle the movement of the camera object.
     *
     * @param event The KeyInputEvent with the pressed key, the action of the key (Press, Pressed or Release),
     *              the window where the key was pressed, the scancode and the mod.
     */
    @SubscribeEvent
    public void move(KeyInputEvent event) {
        if(event.getAction() != GLFW_RELEASE) {
            if (event.getKey() == GLFW_KEY_W) {
                position.z -= moveSpeed;
            } else if (event.getKey() == GLFW_KEY_S) {
                position.z += moveSpeed;
            } else if (event.getKey() == GLFW_KEY_A) {
                position.x -= moveSpeed;
            } else if (event.getKey() == GLFW_KEY_D) {
                position.x += moveSpeed;
            }
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }
}
