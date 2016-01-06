package at.korti.katlonengine.client.input.event;

import at.korti.katlonengine.event.Event;

/**
 * Created by Korti on 06.01.2016.
 */
public class CursorPosEvent extends Event {

    private long window;
    private double xpos;
    private double ypos;

    public CursorPosEvent(long window, double xpos, double ypos) {
        this.window = window;
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public long getWindow() {
        return window;
    }

    public double getXpos() {
        return xpos;
    }

    public double getYpos() {
        return ypos;
    }
}
