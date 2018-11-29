package com.roman.mysan.data.structures.heap;

import java.util.Iterator;

public class PriorityQueue<T extends Comparable<T>> implements Iterable<T> {

    private Object[] heap = new Object[10];
    private int n = 1;

    public void insert(T item) {
        heap[n] = item;
        siftUp(n++);
    }

    public int size() {
        return n-1;
    }

    public T extractMax() {
        T max = (T) heap[1];
        swap(heap, 1, --n);
        heap[n] = null;
        siftDown(1);
        return max;
    }

    public void heapify(Object[] arr) {
        int n = arr.length;
        for (int k = n/2-1; k >= 0; k--) {
            siftDown(heap, k, n);
        }
    }

    public void sort(Object[] arr) {
        heapify(arr);
        int n = arr.length;
        while (n > 1) {
            swap(arr, 0, n-1);
            n--;
            siftDown(arr, 0, n);
        }
    }

    private void siftDown(Object[] heap, int k, int n) {
        while (k*2+1 < n) {
            int j = 2*k+1;
            if (!swapIfNeed(heap, k, j)) {
                break;
            }
            k = j;
        }
    }

    private boolean swapIfNeed(Object[] heap, int i, int j) {
        if (j < n && j+1 < n) {
            if (((T) heap[j]).compareTo(((T) heap[j + 1])) < 0) {
                j++;
            }
        }
        if (((T) heap[i]).compareTo(((T) heap[j])) >= 0) {
            return false;
        }
        swap(heap, i, j);
        return true;
    }

    private void siftDown(int i) {
        while (i * 2 < n) {
            int j = i * 2;
            if (!swapIfNeed(heap, i, j)) {
                break;
            }
            i = j;
        }
    }

    private void siftUp(int i) {
        while (i > 1) {
            if (((T) heap[i/2]).compareTo(((T) heap[i])) < 0) {
                swap(heap, i, i/2);
            } else
                break;
            i /= 2;
        }
    }

    private void swap(Object[] heap, int i, int j) {
        Object tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
        private int i = 1;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public T next() {
            return (T) heap[i++];
        }
    }
}
