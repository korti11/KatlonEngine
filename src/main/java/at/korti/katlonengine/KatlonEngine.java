package at.korti.katlonengine;

import at.korti.katlonengine.client.display.DisplayManager;
import at.korti.katlonengine.event.handler.EventBus;
import at.korti.katlonengine.scene.SceneManager;
import at.korti.katlonengine.util.helper.OpenGLHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Korti on 29.12.2015.
 */
public final class KatlonEngine {

    public static EventBus EVENT_BUS = new EventBus();
    public static Logger logger = LogManager.getLogger("Katlon");
    private static KatlonEngine instance;
    public DisplayManager displayManager;

    private KatlonEngine(){
        displayManager = DisplayManager.instance();
    }

    public static KatlonEngine instance() {
        if (instance == null) {
            instance = new KatlonEngine();
        }
        return instance;
    }

    public void init(){
        displayManager.init();
        OpenGLHelper.initOpenGL();
        logger.info("Katlon Engine is initialized!");
    }

    public void update() {
        displayManager.swapColorBuffers();
        displayManager.pollEvents();
        SceneManager.updateCurrentScene();
    }

    public void render() {
        OpenGLHelper.clearFramebuffer();
        SceneManager.renderCurrentScene();
    }

    public void close(){
        displayManager.close();
        SceneManager.unloadCurrentScene();
        logger.info("Katlon Engine has been closed!");
    }

    public void terminate(){
        displayManager.terminate();
        logger.info("Katlon Engine has been terminated!");
    }

}
