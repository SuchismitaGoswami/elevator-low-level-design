package org.suchismita.interfaces;

public interface IEventQueue<T> {
    void subscribe(ISubscriber observer);
    void unsubscribe(ISubscriber observer);
    void publish(T message);
}
