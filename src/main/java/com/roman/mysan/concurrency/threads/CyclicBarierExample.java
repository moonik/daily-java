package com.roman.mysan.concurrency.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarierExample {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Run());
        new Sportsman(cyclicBarrier).start();
        new Sportsman(cyclicBarrier).start();
        new Sportsman(cyclicBarrier).start();
    }

    static class Run extends Thread {
        @Override
        public void run() {
            System.out.println("Run has began.");
        }
    }

    static class Sportsman extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Sportsman(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                System.out.println("Wooo");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
