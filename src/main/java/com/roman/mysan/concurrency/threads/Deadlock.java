package com.roman.mysan.concurrency.threads;

public class Deadlock {

    public static void main(String[] args) {
        ResourceA a = new ResourceA();
        ResourceB b = new ResourceB();
        new ThreadA(a).start();
        new ThreadB(b).start();
    }
}

class ThreadA extends Thread {
    private ResourceA a;

    public ThreadA(ResourceA a) {
        this.a = a;
    }

    @Override
    public void run() {
        System.out.println(a.getI());
    }
}
class ThreadB extends Thread {
    private ResourceB b;

    public ThreadB(ResourceB b) {
        this.b = b;
    }

    @Override
    public void run() {
        System.out.println(b.getI());
    }
}

class ResourceA {
    private ResourceB b = new ResourceB();

    public synchronized int getI() {
        return b.getI();
    }

    private synchronized int returnI() {
        return 1;
    }
}

class ResourceB {
    private ResourceA a = new ResourceA();

    public synchronized int getI() {
        return a.getI();
    }

    private synchronized int returnI() {
        return 1;
    }
}
