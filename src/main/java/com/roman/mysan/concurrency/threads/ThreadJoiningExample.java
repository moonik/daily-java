package com.roman.mysan.concurrency.threads;

public class ThreadJoiningExample {

    public static void main(String[] args) throws InterruptedException {
        Runnable first = () -> {
            for (int i = 0; i < 1000; i++);
            System.out.println("First");
        };

        Runnable second = () -> {
            System.out.println("Second.");
            for (int i = 0; i < 1000; i++);
        };

        Runnable third = () -> {
            for (int i = 0; i < 1000; i++);
            System.out.println("Third");
        };

        Thread t1 = new Thread(first);
        Thread t2 = new Thread(second);
        Thread t3 = new Thread(third);

        t2.start();
        t2.join();
        t3.start();
        t3.join();
        t1.start();
    }
}
