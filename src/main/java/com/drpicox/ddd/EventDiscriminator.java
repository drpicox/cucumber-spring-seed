package com.drpicox.ddd;

import com.drpicox.ddd.caixesRegistradores.CaixaRegistradoraEvent;
import com.drpicox.queue.MessageListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDiscriminator {

    private Map<Class, MessageListener> listeners = new HashMap<>();
    private MessageListener fallback;

    public <T extends Event> void fallbackListener(MessageListener<T> listener) {
        this.fallback = listener;
    }

    public <T extends Event> void addListener(Class<T> eventType, MessageListener<T> listener) {
        listeners.put(eventType, listener);
    }

    public <T extends Event> void process(T message) {
        var clazz = message.getClass();
        var listener = listeners.get(clazz);
        if (listener == null) listener = fallback;
        if (listener == null) throw new IllegalArgumentException("Message of type '" + message.getClass() + "' is unknown.");

        listener.listen(message);
    }

    public <T extends Event> void processAll(List<T> events) {
        events.forEach(e -> process(e));
    }
}
