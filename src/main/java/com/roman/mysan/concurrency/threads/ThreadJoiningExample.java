package com.roman.mysan.concurrency.threads;

public class ThreadJoiningExample {

    public static void main(String[] args) throws InterruptedException {
        Runnable first = () -> System.out.println("First");
        Runnable second = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Second");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable third = () -> System.out.println("Third");

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
