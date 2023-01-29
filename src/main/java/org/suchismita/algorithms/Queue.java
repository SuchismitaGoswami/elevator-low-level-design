package org.suchismita.algorithms;

import org.suchismita.exceptions.ListUnderflowException;
import org.suchismita.exceptions.QueueUnderFlowException;

public class Queue<T> implements IQueue<T>{

    LinkedList<T> dataStream;
    public Queue(){
        this.dataStream = new LinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return this.dataStream.isEmpty();
    }

    @Override
    public void push(T request) {
        this.dataStream.addLast(request);
    }

    @Override
    public T delete() {
        try {
            return this.dataStream.removeFirst();
        }catch (ListUnderflowException e){
            throw new QueueUnderFlowException("No further request left in the queue");
        }
    }
}
