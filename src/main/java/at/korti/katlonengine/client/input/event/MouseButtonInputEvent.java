package at.korti.katlonengine.client.input.event;

import at.korti.katlonengine.event.Event;

/**
 * Created by Korti on 06.01.2016.
 */
public class MouseButtonInputEvent extends Event{

    private long window;
    private int button;
    private int action;
    private int mods;

    public MouseButtonInputEvent(long window, int button, int action, int mods) {
        this.window = window;
        this.button = button;
        this.action = action;
        this.mods = mods;
    }

    public long getWindow() {
        return window;
    }

    public int getButton() {
        return button;
    }

    public int getAction() {
        return action;
    }

    public int getMods() {
        return mods;
    }
}
