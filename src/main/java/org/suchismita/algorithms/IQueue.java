package org.suchismita.algorithms;

import org.suchismita.models.Request;

public interface IQueue<T> {

    boolean isEmpty();
    void push(T request);
    T delete();
}
