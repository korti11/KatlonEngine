package at.korti.katlonengine.event.eventhandler;

import at.korti.katlonengine.client.input.event.KeyInputEvent;
import at.korti.katlonengine.event.handler.SubscribeEvent;

/**
 * Created by Korti on 29.12.2015.
 */
public class EventHandlerFailTwo {

    @SubscribeEvent
    public void handleKeyInputEvent(KeyInputEvent event, String name) {
        System.out.println("My name is " + name);
        System.out.println("Pressed Key: " + event.getKey());
    }

}
