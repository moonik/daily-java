package com.roman.mysan.data.structures.hashtable;

public interface Map<K, V> {

    /**
     * returns hash code of element
     * @param key
     * @return
     */
    int hash(K key);

    /**
     * if key already exists, update value
     * @param key
     * @param value
     * @return
     */
    void add(K key, V value);

    boolean exists(K key);

    V get(K key);

    Entry<K, V> remove(K key);

    int getCapacity();

    int size();
}
