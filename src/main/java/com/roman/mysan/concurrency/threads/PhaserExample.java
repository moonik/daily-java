package com.roman.mysan.concurrency.threads;

import java.util.concurrent.Phaser;

public class PhaserExample {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(2);
//        new Washer(phaser).start();
//        new Washer(phaser).start();

        CarWasher carWasher = new CarWasher(phaser);
        Runnable firstWorker = carWasher::wash;
        Runnable secondWorker = carWasher::wash;

        phaser.register();
        new Thread(firstWorker).start();
        phaser.arriveAndDeregister();
        phaser.register();
        new Thread(secondWorker).start();
        phaser.arriveAndDeregister();
    }

    static class CarWasher {
        private Phaser phaser;

        public CarWasher(Phaser phaser) {
            this.phaser = phaser;
        }

        public void wash() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + " washing the car " + i);
                phaser.arriveAndAwaitAdvance(); // waits until all threads complete washing i car
            }
        }
    }

    static class Washer extends Thread {
        private Phaser phaser;

        public Washer(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + " washing the car " + i);
                phaser.arriveAndAwaitAdvance(); // waits until all threads complete washing i car
            }
        }
    }
}
