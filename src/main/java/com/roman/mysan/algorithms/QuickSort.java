package com.roman.mysan.algorithms;

public class QuickSort {

    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivot = arr[low + (high - low) / 2];
        int p = partition(arr, low, high, pivot);

        quickSort(arr, low, p-1);
        quickSort(arr, p, high);
    }

    private static int partition(int[] arr, int low, int high, int pivot) {
        int l = low;
        int h = high;

        while (l <= h) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[h] > pivot) {
                h--;
            }

            if (l <= h) {
                int tmp = arr[l];
                arr[l] = arr[h];
                arr[h] = tmp;
                l++;
                h--;
            }
        }
        return l;
    }

    public static int quickSelect(int[] arr, int n) {
        int low = 0;
        int high = arr.length-1;
        while (low < high) {
            int j = partition(arr, low, high, arr[low+(high-low)/2]);

            if (j < n) {
                low = j+1;
            } else if (j > n) {
                high = j-1;
            } else
                return arr[j];
        }
        return arr[n];
    }

    public static void main(String[] args) {
        int[] arr = new int[] {
                325432, 989, 547510, 3, -93, 189019, 5042, 123,
                597, 42, 7506, 184, 184, 2409, 45, 824,
                4, -2650, 9, 662, 3928, -170, 45358, 395,
                842, 7697, 110, 14, 99, 221
        };

        int[] sorted = new int[] {-2650, -170, -93, 3, 4, 9, 14,
                42, 45, 99, 110, 123, 184, 184, 221, 395, 597,
                662, 824, 842, 989, 2409, 3928, 5042, 7506, 7697,
                45358, 189019, 325432, 547510
        };

        QuickSort.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " == " + sorted[i]);
            if (arr[i] != sorted[i]) {
                throw new AssertionError();
            }
        }
        System.out.println("Found: " + quickSelect(arr, 0));
    }
}
