package com.roman.mysan.concurrency.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {

    //static int i = 0;
    static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10_000; i++) {
            new MyThread().start();
        }
        Thread.sleep(2_000);
        System.out.println(i.get());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            //i++; //not atomic operation;
            // atomic operation executes as single, indivisible operation;
            // this may cause race condition;
            // you can use types from atomic package
            // to make operations on that variable as atomic
            i.incrementAndGet();
        }
    }
}
