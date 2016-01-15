package at.korti.katlonengine.util;

/**
 * Created by Korti on 15.01.2016.
 */
public final class Timing {

    private static Timing instance;

    private long oldTime;

    private Timing(){

    }

    public static Timing instance() {
        if (instance == null) {
            instance = new Timing();
            instance.init();
        }
        return instance;
    }

    private long getTime() {
        return System.currentTimeMillis();
    }

    public void init() {
        oldTime = getTime();
    }

    public static long getDeltaTime() {
        long oldTime = instance().oldTime;
        long newTime = instance().getTime();
        long deltaTime = newTime - oldTime;
        instance().oldTime = newTime;
        return deltaTime;
    }

}
