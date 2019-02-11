package com.roman.mysan.data.structures.bloomfilter;

import java.util.Objects;

public class BloomFilter {

    private boolean[] bits;
    private int m; // actual table size
    private int n; // number of entries
    private int hashCount;

    /**
     *
     * @param numberOfElements - number of entries
     * @param falsePositiveProb - false positive probability
     */
    public BloomFilter(int numberOfElements, float falsePositiveProb) {
        n = numberOfElements;
        initializeBits(falsePositiveProb);
        computeHashCount();
    }

    private void computeHashCount() {
        this.hashCount = (int) ((m / n) * Math.log(2));
    }

    private void initializeBits(float falsePositiveProb) {
        this.m = (int) ((n - Math.log(falsePositiveProb)) / Math.pow(Math.log(2), 2));
        this.bits = new boolean[m];
    }

    public void add(Object key) {
        for (int i = 0; i < hashCount; i++) {
            int hash = Objects.hash(key, i) % m;
            bits[hash] = true;
        }
    }

    public boolean check(Object key) {
        for (int i = 0; i < hashCount; i++) {
            int hash = Objects.hash(key, i) % m;
            if (!bits[hash]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter(500, 0.1f);
        bloomFilter.add("Test");
        System.out.println(bloomFilter.check("Test"));
        bloomFilter.add(123);
        System.out.println(bloomFilter.check(123));
        System.out.println(bloomFilter.check(3));
    }
}
