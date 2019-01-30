package com.roman.mysan.concurrency.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {

    private Lock lock = new ReentrantLock();

    public void doSomething()  {
        while (true) {
            if (lock.tryLock()) { // if lock acquired do something...
                System.out.println(Thread.currentThread().getName() + " is working...");
                break;
            } else
                System.out.println(Thread.currentThread().getName() + " is waiting...");
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        TryLockExample example = new TryLockExample();

        Thread first = new Thread(example::doSomething);
        Thread second = new Thread(example::doSomething);

        first.start();
        second.start();
    }
}
