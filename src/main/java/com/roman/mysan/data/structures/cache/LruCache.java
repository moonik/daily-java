package com.roman.mysan.data.structures.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LruCache<K, V> {

    private Map<K, V> cache;
    private final int CAPACITY = 10;
    private LinkedList<K> frequency;

    public LruCache() {
        this.cache = new HashMap<>(CAPACITY);
        this.frequency = new LinkedList<>();
    }

    public void set(K k, V v) {
        if (cache.get(k) == null && cache.size()+1 > CAPACITY) {
            K least = frequency.pollLast();
            cache.remove(least);
        }
        cache.put(k, v);
        changePriority(k);
    }

    public V get(K key) {
        changePriority(key);
        return cache.get(key);
    }

    public void printAll() {
        frequency.forEach(System.out::println);
    }

    private void changePriority(K key) {
        frequency.remove(key);
        frequency.addFirst(key);
    }

    public static void main(String[] args) {
        LruCache<Integer, Integer> cache = new LruCache<>();
        for (int i = 0; i < 10; i++) {
            cache.set(i, i);
        }
        cache.set(12, 12);
        cache.get(2);
        cache.printAll();
    }
}
