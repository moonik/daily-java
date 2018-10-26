package com.roman.mysan.data.structures.hashtable;

public class HashTable<K, V> implements Map<K, V> {

    private Entry<K, V>[] table;
    private static final double MAX_LOAD_FACTOR = 0.6;
    private int m = 13; //capacity
    private int n = 0;

    public HashTable() {
        this.table = new Entry[m];
    }

    @Override
    public int hash(Object key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int hash;
        if (key.getClass() == String.class) {
            hash = handleStringKey((String) key, m);
        } else if (key.getClass() == Character.class) {
            hash = compress((Character) key, m);
        } else
            hash = compress((Integer) key, m);
        return Math.abs(hash);
    }

    @Override
    public void add(K key, V value) {
        int hashCode = hash(key);
        while (table[hashCode] != null) {
            Entry<K, V> entry = table[hashCode];
            if (entry.getKey() == null) {
                break;
            }
            if (entry.getKey().equals(key)) {
                table[hashCode].setValue(value);
                return;
            }
            hashCode = (hashCode + 1) % m;
        }
        table[hashCode] = new Entry<>(key, value);
        n++;
        resizeIfNeed();
    }

    @Override
    public boolean exists(K key) {
        int hashCode = hash(key);
        while (table[hashCode] != null) {
            Entry<K, V> entry = table[hashCode];
            if (entry.getKey() == null) {
                break;
            }
            if (entry.getKey().equals(key)) {
                return true;
            }
            hashCode = (hashCode + 1) % m;
        }
        return false;
    }

    @Override
    public V get(K key) {
        int hashCode = hash(key);
        while (table[hashCode] != null) {
            Entry<K, V> entry = table[hashCode];
            if (entry.getKey() == null) {
                break;
            }
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            } else
                hashCode = (hashCode + 1) % m;
        }
        return null;
    }

    @Override
    public Entry<K, V> remove(K key) {
        int hashCode = hash(key);
        while (table[hashCode] != null) {
            Entry<K, V> entry = table[hashCode];
            if (entry.getKey() == null) {
                return null;
            }
            if (entry.getKey().equals(key)) {
                entry.setKey(null);
                return entry;
            }
            hashCode = (hashCode + 1) % m; // new
        }
        return null;
    }

    @Override
    public int getCapacity() {
        return m;
    }

    @Override
    public int size() {
        return n;
    }

    private double getLoadFactor() {
        return n/(double)m;
    }

    private int handleStringKey(String key, int m) {
        int hash = 0;
        char[] arr = key.toCharArray();
        for (char c : arr) {
            hash = hash * 31 + c;
        }
        return compress(hash, m);
    }

    private int compress(int hashCode, int m) {
        return hashCode % m;
    }

    private void resizeIfNeed() {
        if (getLoadFactor() > MAX_LOAD_FACTOR) {
            m *= 2;
            Entry<K, V>[] buffer = table;
            table = new Entry[m];
            n = 0;
            for (Entry<K, V> item : buffer) {
                if (item != null && item.getKey() != null) {
                    add(item.getKey(), item.getValue());
                }
            }
        }
    }
}
