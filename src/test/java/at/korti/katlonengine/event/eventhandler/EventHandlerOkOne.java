package at.korti.katlonengine.event.eventhandler;

import at.korti.katlonengine.event.KeyInputEvent;
import at.korti.katlonengine.event.handler.SubscribeEvent;

/**
 * Created by Korti on 29.12.2015.
 */
public class EventHandlerOkOne {

    @SubscribeEvent
    public void handleKeyInput(KeyInputEvent event) {
        System.out.println("Pressed key is: " + event.getKey());
    }

}
