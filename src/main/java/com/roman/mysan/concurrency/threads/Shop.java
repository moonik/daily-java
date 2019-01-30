package com.roman.mysan.concurrency.threads;

import java.util.Scanner;

//wait and notify example
//producer - consumer pattern
public class Shop {

    private String item;

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread is waiting...");
            wait();
            System.out.println("Producer resumed.");
            System.out.println("Item: " + item + " produced");
            item = "";
        }
    }

    public void consume() throws InterruptedException {
        //Thread.sleep(2000);

        Scanner in = new Scanner(System.in);

        synchronized (this) {
            System.out.println("What you want to order?");
            item = in.nextLine();
            System.out.println("Order placed.");
            notify(); // can only be called within synchronized block; should be last line because it does not release a lock
        }
    }

    public static void main(String[] args) {
        final Shop shop = new Shop();

        Thread consumer = new Thread(() -> {
            try {
                shop.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread producer = new Thread(() -> {
            try {
                shop.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        consumer.start();
        producer.start();
    }
}
