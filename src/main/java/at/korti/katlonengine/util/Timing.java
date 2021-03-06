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

    /**
     * Get the time between the frames.
     *
     * @return Time between the frames
     */
    public static float getDeltaTime() {
        long oldTime = instance().oldTime;
        long newTime = instance().getTime();
        float deltaTime = (float) (newTime - oldTime) / 1000f;
        instance().oldTime = newTime;
        return deltaTime;
    }

    private long getTime() {
        return System.currentTimeMillis();
    }

    private void init() {
        oldTime = getTime();
    }

}
