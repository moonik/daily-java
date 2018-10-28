package com.roman.mysan;

public class Bitwise {

    private static int findMin(int x, int y) {
        return y ^ ((x ^ y) & -(x < y ? 1 : 0));
    }

    private static int findMax(int x, int y) {
        return x ^ ((x ^ y) & -(x < y ? 1 : 0));
    }

    public static void main(String[] args) {
        System.out.println(findMin(100, 3));
        System.out.println(findMax(100, 4));
    }
}
