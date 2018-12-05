package com.roman.mysan.data.structures;

import com.roman.mysan.data.structures.list.linked.Node;

public class Stack<T extends Comparable<T>> {

    private Node<T> top;
    private int size = 0;

    public boolean empty() {
        return size == 0;
    }

    public void push(T value) {
        top = new Node<>(top, value);
        size++;
    }

    public T pop() {
        if (empty()) {
            return null;
        }
        T value = top.getValue();
        top = top.getNext();
        return value;
    }
}
