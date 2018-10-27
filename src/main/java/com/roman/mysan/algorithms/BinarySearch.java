package com.roman.mysan.algorithms;

public class BinarySearch {

    public static int search(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < key) {
                lo = mid + 1;
            } else if (arr[mid] > key) {
                hi = mid - 1;
            } else
                return mid;
        }
        return -1;
    }

    public static int search(int[] arr, int key, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        int mid = lo + (hi - lo) / 2;
        if (arr[mid] < key) {
            lo = mid + 1;
        } else if (arr[mid] > key) {
            hi = mid - 1;
        } else
            return mid;
        return search(arr, key, lo, hi);
    }
}
