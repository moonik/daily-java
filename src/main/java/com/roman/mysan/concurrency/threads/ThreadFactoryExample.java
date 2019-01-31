package com.roman.mysan.concurrency.threads;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryExample {

    public static void main(String[] args) {
        ThreadFactory threadFactory = r -> {
            Thread thread = new Thread(r);
            thread.setName("My thread");
            thread.setPriority(Thread.MAX_PRIORITY);
            return thread;
        };
        threadFactory.newThread(new MyRun()).start();
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("LOLOLO");
        }
    }
}
