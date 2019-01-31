package com.roman.mysan.concurrency.threads;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Worker(countDownLatch).start();
        new Worker(countDownLatch).start();
        new Worker(countDownLatch).start();

        countDownLatch.await(); // thread goes sleep and waits until counter become 0
        System.out.println("All workers finished their job.");
    }
}

class Worker extends Thread {
    private CountDownLatch countDownLatch;

    public Worker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is finished.");
        countDownLatch.countDown();
    }
}
