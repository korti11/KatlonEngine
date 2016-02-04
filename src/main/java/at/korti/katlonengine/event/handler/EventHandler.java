package at.korti.katlonengine.event.handler;

import at.korti.katlonengine.event.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Korti on 29.12.2015.
 */
public class EventHandler {

    private Map<Object, Set<Method>> handlers;

    public EventHandler() {
        handlers = new HashMap<>();
    }

    /**
     * Added a method that can handle that event.
     *
     * @param target  Object that has the method.
     * @param handler The method that can handle the event.
     */
    public void addHandler(Object target, Method handler) {
        if (!handlers.containsKey(target)) {
            Set<Method> methods = new HashSet<>();
            methods.add(handler);
            handlers.put(target, methods);
            return;
        } else {
            Set<Method> methods = handlers.get(target);
            methods.add(handler);
        }
    }

    /**
     * Invoke all method that can handle the event.
     * @param event Event.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void invoke(Event event) throws InvocationTargetException, IllegalAccessException {
        for (Map.Entry<Object, Set<Method>> entry : handlers.entrySet()) {
            for (Method method : entry.getValue()) {
                if (event.isCancelable() && event.isCanceled()) {
                    return;
                }
                method.invoke(entry.getKey(), event);
            }
        }
    }

}
