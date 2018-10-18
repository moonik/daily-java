package com.roman.mysan.data.structures.list.linked;

public class MyLinkedList<T> implements List<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public T valueAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size-1) {
            return tail.getValue();
        }
        Node<T> current = head;
        while (index != 0) {
            current = current.getNext();
            index--;
        }
        return current.getValue();
    }

    @Override
    public void pushFront(T value) {
        head = new Node<>(head, value);
    }

    @Override
    public T popFront() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }
        T value = head.getValue();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    @Override
    public void pushBack(T value) {
        Node<T> node = new Node<>(null, value);
        if (empty()) {
            head = node;
            tail = head;
        } else {
            node.setNext(tail);
            tail = node;
        }
        size++;
    }

    @Override
    public T popBack() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().equals(tail)) {
                T value = current.getNext().getValue();
                tail = current;
                current.setNext(null);
                size--;
                return value;
            }
        }
        return popFront(); //if there is only 1 element in the list
    }

    @Override
    public T front() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }
        return head.getValue();
    }

    @Override
    public T back() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }
        return tail.getValue();
    }

    @Override
    public void insert(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        while (index-1 != 0) {
            index--;
            current = current.getNext();
        }
        Node<T> currentNext = current.getNext();
        Node<T> node = new Node<>(currentNext, value);
        current.setNext(node);
        if (node.getNext() == null) {
            tail = node;
        }
        size++;
    }

    @Override
    public void erase(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            popFront();
            return;
        }
        if (index == size-1) {
            popBack();
            return;
        }
        Node<T> current = head;
        while (index-1 != 0) {
            index--;
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
        if (current.getNext() == null) {
            tail = current;
        }
        size--;
    }

    @Override
    public T valueNfromEnd(int n) {
        if (n < 0 || n >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (n == 0) {
            return tail.getValue();
        }
        if (n == size-1) {
            return head.getValue();
        }
        Node<T> current = head;
        while (n != 0) {
            current = current.getNext();
            n--;
        }
        return current.getValue();
    }

    @Override
    public void reverse() {
        if (empty()) {
            return;
        }

        Node<T> current = head;
        Node<T> prev = null;
        tail = current;
        while (current.getNext() != null) {
            current.setNext(prev);
            prev = current;
            current = current.getNext();
        }
        current.setNext(prev);
        head = current;
    }

    @Override
    public void removeValue(T value) {
        if (empty()) {
            return;
        }
        if (head.getValue().equals(value)) {
            popFront();
        } else if (tail.getValue().equals(value)) {
            popBack();
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                if (current.getNext().getValue().equals(value)) {
                    current.setNext(current.getNext().getNext());
                    if (current.getNext() == null) {
                        tail = current;
                    }
                }
            }
        }
    }
}
