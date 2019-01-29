package com.roman.mysan.concurrency;

public class SimpleSemaphore {

    private volatile int resources;

    public SimpleSemaphore() {
        this.resources = 1;
    }

    public SimpleSemaphore(int resources) {
        this.resources = resources;
    }

    public synchronized void acquire() {
        while (resources == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                resources--;
            }
        }
    }

    public synchronized void release() {
        resources++;
        if (resources > 0) {
            notify();
        }
    }
}
