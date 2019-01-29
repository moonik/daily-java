package com.roman.mysan.concurrency.threads;

public class VolatileExample {
    volatile static int i = 0; //for processor x86 all variables are volatile by default

    public static void main(String[] args) {
        new Read().start();
        new Write().start();
    }

    static class Write extends Thread {
        @Override
        public void run() {
            while (i < 5) {
                i++;
                System.out.println("i incremented: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Read extends Thread {
        @Override
        public void run() {
            int var = i;
            while (var < 5) {
                if (var != i) { // without volatile keyword on i - this will never get executed, because value of i was cached in thread
                    System.out.println("i value is: " + i);
                    var = i;
                }
            }
        }
    }
}
