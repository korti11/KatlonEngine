package at.korti.katlonengine;

import at.korti.katlonengine.client.display.DisplayManager;
import at.korti.katlonengine.event.handler.EventBus;

/**
 * Created by Korti on 29.12.2015.
 */
public class KatlonEngine {

    private static KatlonEngine instance;
    public static EventBus EVENT_BUS = new EventBus();

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
    }

    public void close(){
        displayManager.close();
    }

    public void terminate(){
        displayManager.terminate();
    }

}
