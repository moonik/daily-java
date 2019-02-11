package com.roman.mysan.concurrency.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {


    public void acquireLocks(Lock lock1, Lock lock2) {
        while (true) {
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                gotFirstLock = lock1.tryLock();
                gotSecondLock = lock2.tryLock();
            }finally {
                if (gotFirstLock && gotSecondLock) {
                    System.out.println("Got all locks");
                    return;
                } else {
                    if (gotFirstLock) {
                        lock1.unlock();
                    } else
                        lock2.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ResourceA a = new ResourceA();
        ResourceB b = new ResourceB();
        new ThreadA(a).start();
        new ThreadB(b).start();
    }
}

class ThreadA extends Thread {
    private ResourceA a;

    public ThreadA(ResourceA a) {
        this.a = a;
    }

    @Override
    public void run() {
        System.out.println(a.getI());
    }
}

class ThreadB extends Thread {
    private ResourceB b;

    public ThreadB(ResourceB b) {
        this.b = b;
    }

    @Override
    public void run() {
        System.out.println(b.getI());
    }
}

class ResourceA {
    private ResourceB b = new ResourceB();

    public synchronized int getI() {
        return b.getI();
    }

    private synchronized int returnI() {
        return 1;
    }
}

class ResourceB {
    private ResourceA a = new ResourceA();

    public synchronized int getI() {
        return a.getI();
    }

    private synchronized int returnI() {
        return 1;
    }
}
