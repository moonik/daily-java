package com.roman.mysan.algorithms;

public class SimpleSort {

    public static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = tmp;
                } else
                    break;
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
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

        SimpleSort.insertionSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " == " + sorted[i]);
            if (arr[i] != sorted[i]) {
                throw new AssertionError();
            }
        }
        arr = new int[] {
                325432, 989, 547510, 3, -93, 189019, 5042, 123,
                597, 42, 7506, 184, 184, 2409, 45, 824,
                4, -2650, 9, 662, 3928, -170, 45358, 395,
                842, 7697, 110, 14, 99, 221
        };
        
        SimpleSort.selectionSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " == " + sorted[i]);
            if (arr[i] != sorted[i]) {
                throw new AssertionError();
            }
        }
    }
}
