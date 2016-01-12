package at.korti.katlonengine;

import at.korti.katlonengine.client.display.DisplayManager;
import at.korti.katlonengine.client.render.MasterRenderer;
import at.korti.katlonengine.event.handler.EventBus;
import at.korti.katlonengine.util.helper.OpenGLHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Korti on 29.12.2015.
 */
public class KatlonEngine {

    private static KatlonEngine instance;
    public static EventBus EVENT_BUS = new EventBus();
    public static Logger logger = LogManager.getLogger("Katlon");

    public DisplayManager displayManager;
    public MasterRenderer masterRenderer;

    private KatlonEngine(){
        displayManager = DisplayManager.instance();
        masterRenderer = MasterRenderer.instance();
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

    public void render() {
        masterRenderer.renderAll();
    }

    public void close(){
        displayManager.close();
        masterRenderer.cleanUp();
        logger.info("Katlon Engine has been closed!");
    }

    public void terminate(){
        displayManager.terminate();
        logger.info("Katlon Engine has been terminated!");
    }

}
