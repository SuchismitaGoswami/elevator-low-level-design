package org.suchismita.interfaces;

public interface ISubscriber<T> {
    void consume(T message);
}
