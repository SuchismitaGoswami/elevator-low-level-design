package org.suchismita.algorithms;

import org.suchismita.exceptions.ListUnderflowException;


public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public T removeFirst() throws ListUnderflowException{
        if(this.head == null){
            throw new ListUnderflowException("List Underflow!");
        }else{
            Node<T> node = this.head;
            this.head = this.head.getNext();
            node.setNext(null);
            return node.getData();
        }
    }
    public void addLast(T data){
        Node<T> newNode = new Node<>(data);
        if(this.isEmpty()){
            this.head = this.tail = newNode;
        }else{
            this.tail.setNext(newNode);
            this.tail = this.tail.getNext();
        }
    }

    public boolean isEmpty() {
        return this.head == this.tail;
    }
}
