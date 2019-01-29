package com.roman.mysan.concurrency.threads;

import java.sql.ResultSet;
import java.util.Objects;

public class SynchronizationExample {

    public static void main(String[] args) {
        Resource resource = new Resource();
        MyThread thread1 = new MyThread(resource);
        MyThread thread2 = new MyThread(resource);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread extends Thread {
    private Resource resource;

    public MyThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        //resource.test();
        try {
            resource.change();
            Resource.changeStatic();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Resource {

    public Object object;
    public static int i = 1;

    //Now only thread will hold the lock
    public synchronized void change() throws InterruptedException {
        //synchronized (this) {} - lock is only on object
        //synchronized (ResultSet.class) {} - lock on whole class
        if (object == null) {
            if (Thread.currentThread().getName().equals("Thread-0")) {
                Thread.sleep(100); // just to make thread wait so they can access method at the same time
            }
            object = new Object(); // both threads will create an object if method is not synchronized
        }
        System.out.println(Objects.hashCode(object)); //without synchronization (lock) hashcodes will differ
    }

    public static synchronized void changeStatic() throws InterruptedException {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            Thread.sleep(100); // just to make thread wait so they can access method at the same time
        }
        i++;
    }

    //Monitor (lock) behaves like mutex (only 1 thread can access at a time)
    public synchronized void test() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread: " + Thread.currentThread().getName() + "; i = " + i);
        }
    }
}
