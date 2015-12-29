package at.korti.katlonengine;

import at.korti.katlonengine.client.DisplayManager;

/**
 * Created by Korti on 29.12.2015.
 */
public class KatlonEngine {

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
    }

    public void close(){
        displayManager.close();
    }

    public void terminate(){
        displayManager.terminate();
    }

}
