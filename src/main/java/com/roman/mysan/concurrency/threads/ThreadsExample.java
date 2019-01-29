package com.roman.mysan.concurrency.threads;

public class ThreadsExample extends Thread{

    @Override
    public void run() {
        System.out.println("Thread name: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new ThreadsExample();
        Thread thread = new Thread(new MyRunnable());
        myThread.start();
        thread.start();
        thread.join(); // thread main will wait for thread to complete
        System.out.println("Thread in main: " + Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread in Runnable " + Thread.currentThread().getName());
    }
}
