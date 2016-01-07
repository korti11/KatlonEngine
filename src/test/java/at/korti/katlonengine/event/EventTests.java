package at.korti.katlonengine.event;

import at.korti.katlonengine.client.input.event.KeyInputEvent;
import at.korti.katlonengine.event.eventhandler.EventHandlerFailOne;
import at.korti.katlonengine.event.eventhandler.EventHandlerFailTwo;
import at.korti.katlonengine.event.eventhandler.EventHandlerOkOne;
import at.korti.katlonengine.event.handler.EventBus;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.fail;

/**
 * Created by Korti on 29.12.2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventTests {

    @Test
    public void t001FireKeyInputEvent_ok() {
        EventBus eventBus = new EventBus();
        eventBus.registerEvent(KeyInputEvent.class);
        eventBus.register(new EventHandlerOkOne());
        eventBus.fireEvent(new KeyInputEvent(0, 0, 0, 0, 0));
    }

    @Test
    public void t100FireKeyInputEventNotRegister_fail(){
        EventBus eventBus = new EventBus();
        try {
            eventBus.register(new EventHandlerOkOne());
        } catch (Exception e) {
            System.err.println(e);
            return;
        }
        fail();
    }

    @Test
    public void t101RegisterKeyInputHandler1_fail() {
        EventBus eventBus = new EventBus();
        eventBus.registerEvent(KeyInputEvent.class);
        try {
            eventBus.register(new EventHandlerFailOne());
        } catch (Exception e) {
            System.err.println(e);
            return;
        }
        fail();
    }

    @Test
    public void t102RegisterKeyInputHandler2_fail() {
        EventBus eventBus = new EventBus();
        eventBus.registerEvent(KeyInputEvent.class);
        try {
            eventBus.register(new EventHandlerFailTwo());
        } catch (Exception e) {
            System.err.println(e);
            return;
        }
        fail();
    }

}
