package at.korti.katlonengine.event.handler;

import at.korti.katlonengine.event.Event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Korti on 29.12.2015.
 */
public class EventBus {

    private Map<Class<? extends Event>, EventHandler> eventHandlers;

    public EventBus() {
        eventHandlers = new HashMap<>();
    }

    public void register(Object target) {
        for (Method method : target.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(SubscribeEvent.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new IllegalArgumentException(
                            "Method " + method + " has a @SubscribeEvent annotation, but requires " + parameterTypes.length
                                    + " arguments. Event handler methods must require a single argument."
                    );
                }

                Class<?> eventType = parameterTypes[0];

                if (!Event.class.isAssignableFrom(eventType)) {
                    throw new IllegalArgumentException("Method " + method + " has a @SubscribeEvent annotation, but takes a argument that is not an Event " + eventType);
                }
                else if (!eventHandlers.containsKey(eventType)) {
                    throw new IllegalStateException("Method " + method + " takes a Event as argument that is not registered.");
                }

                EventHandler handler = eventHandlers.get(eventType);
                handler.addHandler(target, method);
            }
        }
    }

    public void registerEvent(Class<? extends Event> event) {
        if (eventHandlers.containsKey(event)) {
            return;
        }
        eventHandlers.put(event, new EventHandler());
    }

    public void fireEvent(Event event) {
        EventHandler handler = eventHandlers.get(event.getClass());
        try {
            handler.invoke(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
