package com.roman.mysan.concurrency.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizationSecondExample {

    private Lock firstLock = new ReentrantLock();
    private Lock secondLock = new ReentrantLock();

    private final Object o1 = new Object();
    private final Object o2 = new Object();

    private List<Integer> firstList = new ArrayList<>();
    private List<Integer> secondList = new ArrayList<>();

    public void firstMethod() {
        //first method: use objects to create different locks
//        synchronized (o1) {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            firstList.add(1);
//        }

        firstLock.lock();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        firstList.add(1);

        firstLock.unlock();
    }

    public void secondMethod() {
        //first method: use objects to create different locks
//        synchronized (o2) {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            secondList.add(1);
//        }
        //instead use already created locks

        secondLock.lock();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        secondList.add(1);
        secondLock.unlock();
    }

    public void doSomething() {
        for (int i = 0; i < 1000; i++) {
            firstMethod();
            secondMethod();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizationSecondExample example = new SynchronizationSecondExample();
        Thread t1 = new Thread(example::doSomething);
        Thread t2 = new Thread(example::doSomething);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(example.firstList.size());
        System.out.println(example.secondList.size());
    }
}
