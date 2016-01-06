package at.korti.katlonengine.client.input.event;

import at.korti.katlonengine.event.Event;

/**
 * Created by Korti on 06.01.2016.
 */
public class ScrollInputEvent extends Event {

    private long window;
    private double xoffset;
    private double yoffset;

    public ScrollInputEvent(long window, double xoffset, double yoffset) {
        this.window = window;
        this.xoffset = xoffset;
        this.yoffset = yoffset;
    }

    public long getWindow() {
        return window;
    }

    public double getXoffset() {
        return xoffset;
    }

    public double getYoffset() {
        return yoffset;
    }
}
