package org.suchismita.models;

import org.suchismita.interfaces.IEventQueue;
import org.suchismita.interfaces.ISubscriber;
import org.suchismita.algorithms.Queue;

import java.util.ArrayList;


public class RequestQueue<Request> implements IEventQueue<Request> {

    private Queue<Request> datastore = new Queue<Request>();
    private ArrayList<ISubscriber<Request>> subscribers;

    public RequestQueue(){
        this.datastore = new Queue<Request>();
        this.subscribers = new ArrayList<>();
    }

    @Override
    public synchronized void subscribe(ISubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public synchronized void unsubscribe(ISubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void publish(Request request) {
        this.datastore.push(request);
        this.subscribers.forEach(subscribers->subscribers.consume(request));
    }
}
