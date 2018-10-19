package com.roman.mysan.data.structures.queue;

public class ArrayQueue<T> {

    private Object[] arr;
    private int read = 0;
    private int write = 0;
    private int size = 10;

    public ArrayQueue() {
        this.arr = new Object[size];
    }

    public void enqueue(T value) {
        if (write == size) {
            write = 0;
        }
        arr[write++] = value;
    }

    public T dequeue() {
        T value = (T) arr[read];
        arr[read++] = null;

        if (read == size) {
            read = 0;
        }
        return value;
    }

    public boolean full() {
        return read == write+1 % size; // write == size || write+1 == read
    }
}
