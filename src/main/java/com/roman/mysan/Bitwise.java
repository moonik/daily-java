package com.roman.mysan;

public class Bitwise {

    private static int findMin(int x, int y) {
        return y ^ ((x ^ y) & -(x < y ? 1 : 0));
    }

    private static int findMax(int x, int y) {
        return x ^ ((x ^ y) & -(x < y ? 1 : 0));
    }

    private static boolean detectIfOppositeSigns(int x, int y) {
        return (x ^ y) < 0;
    }

    private static void setOrClearBits(int n, int m, int set) {
        System.out.println(Integer.toBinaryString(m));
        System.out.println(Integer.toBinaryString(n));
        int w = (n & ~m) | (-set & m);
        System.out.println(Integer.toBinaryString(w));
    }

    private static void negate(int n, int neg) {
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(-neg));
        int r = (n ^ -neg) + neg;
        System.out.println(Integer.toBinaryString(r));
    }

    private static void merge(int a, int b, int mask) {
        int r = a ^ ((a ^ b) & mask);
        System.out.println(Integer.toBinaryString(r));
    }

    private static void countBits(int n) {
        int counter = 0;
        for (; n > 0; n >>>= 1) {
            counter += n & 1;
        }
        System.out.println(counter);
    }

    public static void main(String[] args) {
        //System.out.println(detectIfOppositeSigns(-1, -1));
        //setOrClearBits(10, 8, 0);
        //negate(1, 1);
        merge(34, 1, 4);
        //countBits(15);
    }
}
