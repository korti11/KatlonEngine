package at.korti.katlonengine.event.eventhandler;

import at.korti.katlonengine.event.handler.SubscribeEvent;

/**
 * Created by Korti on 29.12.2015.
 */
public class EventHandlerFailOne {

    @SubscribeEvent
    public void handleKeyInput(int key) {
        System.out.println("Pressed Key: " + key);
    }

}
