package com.roman.mysan.data.structures.queue;

import com.roman.mysan.data.structures.list.linked.Node;

public class Queue<T> {
    private Node<T> head;
    private Node<T> tail;

    public boolean isEmpty() {
        return tail == null;
    }

    /**
     * for head + tail pointers
     * @param value
     */
    public void enqueueH(T value) {
        Node<T> n = new Node<>(null, value);
        if (isEmpty()) {
            head = tail = n;
        } else {
            tail.setNext(n);
            tail = n;
        }
    }

    /**
     * only with tail pointer
     * @param value
     */
    public void enqueue(T value) {
        tail = new Node<>(tail, value);
    }

    public T dequeueH() {
        T value = head.getValue();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public T dequeue() {
        Node<T> prev = null;
        Node<T> current = tail;

        while (current.getNext() != null) {
            prev = current;
            current = current.getNext();
        }

        T value = current.getValue();

        if (prev == null) {
            tail = null;
        } else
            current.setNext(null);

        return value;
    }
}
