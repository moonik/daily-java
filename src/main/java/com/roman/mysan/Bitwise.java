package com.roman.mysan;

import com.roman.mysan.data.structures.list.linked.Node;

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

    /**
     * if set = 0 then clear if set = 1 then set
     * @param n
     * @param m
     * @param set
     */
    private static void setOrClearBits(int n, int m, int set) {
        System.out.println(Integer.toBinaryString(n));
        int mask = 1 << m;
        n = (n & ~mask) | (-set & mask);
        System.out.println(Integer.toBinaryString(n));
    }

    /**
     * if neg = 1 then neg if 0 don't
     * @param n
     * @param neg
     */
    private static void negate(int n, int neg) {
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(-neg));
        int r = (n ^ -neg) + neg;
        System.out.println(r);
    }

    /**
     * Merge bits from two values according to a mask
     * @param a
     * @param b
     * @param mask - 1 where bits from b should be selected; 0 where from a.
     */
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

    /**
     * The following finds the rank of a bit, meaning it returns the sum of bits that are set to 1 from the
     * most-signficant bit downto the  bit at the given position.
     * @param n
     * @param pos
     */
    private static void getRank(int n, int pos) {
        System.out.println(Integer.toBinaryString(n));
        n = n >>> pos;
        int c = 0;
        for (; n > 0; n >>>=1) {
            c += n & 1;
        }
        System.out.println(c);
    }

    /**
     * counts a total number of 1s and check if odd(true) or even (false)
     * @param v
     */
    private static void parity(int v) {
        boolean parity = false;
        while(v > 0)  {
            parity = !parity;
            v = v & (v - 1);
        }
    }

    private static void swap(int a, int b) {
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println(Integer.toBinaryString(4-1));
    }

    private static void reverse(int v) {
        System.out.println(Integer.toBinaryString(v));
        int r = v; // r will be reversed bits of v; first get LSB of v
        int s = 31; // extra shift needed at end

        for (v >>= 1; v > 0; v >>= 1) {
            r <<= 1;
            r |= v & 1;
            s--;
        }
        r <<= s; // shift when v's highest bits are zero
        System.out.println(Integer.toBinaryString(r));
    }

    private static void modulo(int n, int s) {
        int d = 1 << s; // So d will be one of: 1, 2, 4, 8, 16, 32, ...
        int m;                // m will be n % d
        m = n & (d - 1);
        System.out.println(m);
    }

    private static int logTwo(int n) {
        int r = 0; // r will be lg(v)

        while ((n >>= 1) > 0) {
            r++;
        }
        return r;
    }

    private static void consecutive(int v) {
        int c;  // output: c will count v's trailing zero bits,
        // so if v is 1101000 (base 2), then c will be 3
        if (v > 0) {
            v = (v ^ (v - 1)) >> 1;  // Set v's trailing 0s to 1s and zero rest
            for (c = 0; v > 0; c++) {
                v >>= 1;
            }
        }
        else {
            c = 31;
        }
        System.out.println(c);
    }

    private static int nextPowerOfTwo(int n) {
        --n;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        return ++n;
    }

    private static float absOfFloat(float x) {
        // copy and re-interpret as 32 bit integer
        int casted = (int)x;
        // clear highest bit
        casted &= 0x7FFFFFFF;

        // re-interpret as float
        return (float)casted;
    }

    private static int lowestBitNotSet(int x) {
        return ~x & (x + 1);
    }

    private static int lowestBitNotSetSimple(int x) {
        // to be consistent with lowestBitNotSet
        if (x == ~0)
            return 0;

        // shift right until finding a zero
        int result = 1;
        while ((x & result) != 0)
            result <<= 1;
        return result;
    }

    private static boolean checkIfHasZeroByte(int x) {
        if ((x & 0x000000FF) == 0)
            return true;
        if ((x & 0x0000FF00) == 0)
            return true;
        if ((x & 0x00FF0000) == 0)
            return true;
        if ((x & 0xFF000000) == 0)
            return true;
        return false;
    }

    static int extend(int value, int smallSize, int bigSize) {
            // i.e. to extend from 5 to 8 bits, use extend(x,5,8)
        int leftShift  = bigSize - smallSize;
        int rightShift = bigSize - leftShift;
        // extend and fill lower bits smartly
        return (value << leftShift) | (value >> rightShift);
    }

    static float invSquareRoot(float x) {
        // for Newton iteration
        float xHalf = 0.5f*x;
        // same as above
        int i = (int) x;
        i = 0x5F375A86 - (i>>1);
        // one Newton iteration, repeating further improves precision
        return x * (1.5f - xHalf*x*x);
    }

    int rotateLeft(int x, int n) {
        return (x << n) | (x >> (-n & 31));
    }
    
    public static void main(String[] args) {
        //System.out.println(invSquareRoot(9));
        //System.out.println(detectIfOppositeSigns(-1, -1));
        //setOrClearBits(15, 1, 0);
        //negate(1, 1);
        //merge(34, 1, 4);
        //countBits(15);
        //getRank(31, 3);
        //parity(8);
        //swap(1, 2);
        //reverse(29);
        //modulo(8, 4);
        //System.out.println(logTwo(9));
        //consecutive(8);
        //System.out.println(nextPowerOfTwo(9));
        //System.out.println(absOfFloat(-2.123f));
        //System.out.println(lowestBitNotSetSimple(2));
        //System.out.println((extend(3, 4, 16)));
    }
}
