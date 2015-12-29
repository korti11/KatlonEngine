package at.korti.katlonengine.event;

/**
 * Created by Korti on 29.12.2015.
 */
public class Event {

    private boolean cancelable = false;
    private boolean isCanceled = false;

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void cancelEvent() {
        this.isCanceled = true;
    }
}
