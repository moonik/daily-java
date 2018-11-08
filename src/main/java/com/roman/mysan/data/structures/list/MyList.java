package com.roman.mysan.data.structures.list;

import java.util.Arrays;
import java.util.Collection;

public class MyList<T> implements List<T> {

    private Object[] arr;
    private int capacity = 16;
    private int size = 0;

    public MyList() {
        arr = new Object[capacity];
    }

    public MyList(int size) {
        checkMultipleOfEight(size);
        arr = new Object[capacity];
    }

    public MyList(Collection<T> collection) {
        checkMultipleOfEight(collection.size());
        arr = Arrays.copyOf(collection.toArray(), capacity);
        size = collection.size();
    }

    private void checkMultipleOfEight(int size) {
        if (size > capacity) {
//            size = --size >> 2;
//            capacity = (int) Math.pow(2, ++size);
            capacity = (int) Math.pow(2, Math.ceil(Math.log(size)/Math.log(2)));
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T at(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) arr[index];
    }

    @Override
    public void push(T item) {
        if (size >= capacity) {
            resize(capacity*2);
        }
        arr[size++] = item;
    }

    @Override
    public void insert(int index, T item) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        size++;
        if (size >= capacity) {
            resize(capacity*2);
        }
        for (; index < size; index++) {
            Object tmp = arr[index];
            arr[index] = item;
            item = (T) tmp;
        }
    }

    @Override
    public void prepend(T item) {
        size++;

        if (size >= capacity) {
            resize(capacity*2);
        }

        for (int i = 0; i < size; i++) {
            Object tmp = arr[i];
            arr[i] = item;
            item = (T) tmp;
        }
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T item = (T) arr[size--];
        arr[size] = null;
        if (size < capacity/4) {
            resize(capacity/2);
        }
        return item;
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (; index < size-1; index++) {
            arr[index] = arr[index+1];
        }
        arr[--size] = null;
        if (size < capacity/4) {
            resize(capacity/2);
        }
    }

    @Override
    public void remove(T item) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                delete(i);
                i--;
            }
        }
        System.out.println(arr);
        if (size < capacity/4) {
            resize(capacity/2);
        }
    }

    @Override
    public int find(T item) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    private void resize(int s) {
        capacity = s;
        arr = Arrays.copyOf(arr, capacity);
    }
}
