package com.roman.mysan.algorithms;

public class MergeSort {

    public static void sort(Comparable[] arr) {
        mergeSort(arr, new Comparable[arr.length], 0, arr.length-1);
    }

    private static void mergeSort(Comparable[] arr, Comparable[] aux, int l, int r) {
        if (l >= r) {
            return;
        }

        int mid = l + (r - l) / 2;

        mergeSort(arr, aux, l, mid);
        mergeSort(arr, aux, mid+1, r);
        merge(arr, aux, l, mid, r);
    }

    private static void merge(Comparable[] arr, Comparable[] aux, int leftStart, int mid, int rightEnd) {
        int l = leftStart;
        int r = mid + 1;
        int i = leftStart;

        while (l <= mid && r <= rightEnd) {
            int cmp = arr[l].compareTo(arr[r]);
            if (cmp <= 0) {
                aux[i++] = arr[l++];
            } else
                aux[i++] = arr[r++];
        }

        // copies the rest of items from the left half
        // from source array(arr) starting from l position to
        // destination array(aux) starting from position i
        // length of elements mid - l + 1
        System.arraycopy(arr, l, aux, i, mid - l + 1);
        // copies the rest of items from the right half
        // copies from source array(arr) starting from r position to
        // destination array(aux) starting from position i
        // length of elements rightEnd - r + 1
        System.arraycopy(arr, r, aux, i, rightEnd - r + 1);
        // copies all items from source array(aux) starting from leftStart position to
        // destination array(arr) starting from position leftStart
        // length of elements rightEnd - leftStart + 1
        // here is important that we track the start position (leftStart)
        // because aux array was created from the start
        // we need to track which elements we want to copy
        System.arraycopy(aux, leftStart, arr, leftStart, rightEnd - leftStart + 1);
    }

    public static void main(String[] args) {
        System.out.println("====> Running Merge Sort tests...");
        Integer[] arr = {99, 7, 4, 34, 0, 2, 3, -11, 5};
        sort(arr);
        Integer[] expected = {-11, 0, 2, 3, 4, 5, 7, 34, 99};
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals(expected[i])) {
                throw new AssertionError("Expected " + arr[i] + " == " + expected[i]);
            }
        }
        System.out.println("====> Merge Sort passed!");
    }
}
