package com.roman.mysan.data.structures.list;

public interface List<T> {
    int size();
    int capacity();
    boolean isEmpty();

    /**
     * return the value at index
     * @param index
     * @return
     */
    T at(int index);

    /**
     * adds item to the end of array
     * @param item
     */
    void push(T item);

    /**
     * inserts the item at index
     * @param index
     * @param item
     */
    void insert(int index, T item);

    /**
     * inserts item at 0 index
     * @param item
     */
    void prepend(T item);

    /**
     * removes last item
     * @return
     */
    T pop();
    void delete(int index);
    void remove(T item);
    int find(T item);
}
