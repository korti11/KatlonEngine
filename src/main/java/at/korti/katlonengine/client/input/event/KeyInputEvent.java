package at.korti.katlonengine.client.input.event;

import at.korti.katlonengine.event.Event;

/**
 * Created by Korti on 29.12.2015.
 */
public class KeyInputEvent extends Event {

    private long window;
    private int key;
    private int scanCode;
    private int action;
    private int mods;

    public KeyInputEvent(long window, int key, int scanCode, int action, int mods) {
        this.window = window;
        this.key = key;
        this.scanCode = scanCode;
        this.action = action;
        this.mods = mods;
    }

    public long getWindow() {
        return window;
    }

    public int getKey() {
        return key;
    }

    public int getScanCode() {
        return scanCode;
    }

    public int getAction() {
        return action;
    }

    public int getMods() {
        return mods;
    }
}
