package com.roman.mysan.concurrency.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    public static void main(String[] args) {
        ResourceClass resource = new ResourceClass();

        Thread first = new Thread(() -> {
            try {
                resource.firstInstance();
                resource.secondInstance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread second = new Thread(() -> {
            try {
                resource.firstInstance();
                resource.secondInstance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        first.start();
        second.start();
    }
}

class ResourceClass {
    private Lock lock = new ReentrantLock();
    public Object first;
    public Object second;

    public void firstInstance() throws InterruptedException {
        lock.lock(); // acquire lock here   <=======================================================================
        System.out.println(Thread.currentThread().getName() + " got the lock");
        if (first != null) {
            if (Thread.currentThread().getName().equals("Thread-0")) {
                Thread.sleep(100); // just to make thread wait so they can access method at the same time
            }
            first = new Object();
        }
    }

    public void secondInstance() throws InterruptedException {
        if (second != null) {
            if (Thread.currentThread().getName().equals("Thread-1")) {
                Thread.sleep(100); // just to make thread wait so they can access method at the same time
            }
            second = new Object();
        }
        lock.unlock(); //release lock here  <=======================================================================
        System.out.println(Thread.currentThread().getName() + " released the lock");
    }
}
