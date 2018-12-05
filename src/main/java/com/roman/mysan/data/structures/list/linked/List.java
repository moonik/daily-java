package com.roman.mysan.data.structures.list.linked;

public interface List<T extends Comparable<T>> {
    int size();
    boolean empty();

    /**
     * returns the value of the nth item
     * @param index
     * @return
     */
    T valueAt(int index);

    /**
     * adds and item to the front of the list
     * @param value
     */
    void pushFront(T value);

    /**
     * removes front item and returns its value
     * @return
     */
    T popFront();

    /**
     * adds an item at the end
     * @param value
     */
    void pushBack(T value);

    /**
     * removes end item and returns its value
     * @return
     */
    T popBack();

    /**
     * pop back for linked list without tail
     * @return
     */
    T popBackWithNoTail();

    /**
     * get value of front item
     * @return
     */
    T front();

    /**
     * get value of end item
     * @return
     */
    T back();

    /**
     * insert value at index, so current item at that index is pointed to by new item at index
     * @param index
     * @param value
     */
    void insert(int index, T value);

    /**
     * removes node at given index
     * @param index
     */
    void erase(int index);

    /**
     * returns the value of the node at nth position from the end of the list
     * @param n
     * @return
     */
    T valueNfromEnd(int n);

    /**
     * reverses the list
     */
    void reverse();

    /**
     * removes the first item in the list with this value
     * @param value
     */
    void removeValue(T value);

    Node<T> getMiddle();
}
