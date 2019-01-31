package com.roman.mysan.concurrency.threads;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant(2);
        Person person = new Person(restaurant);
        Person person1 = new Person(restaurant);
        Person person2 = new Person(restaurant);
        Person person3 = new Person(restaurant);
        Person person4 = new Person(restaurant);
        Person person5 = new Person(restaurant);
        Person person6 = new Person(restaurant);

        person.start();
        person1.start();
        person2.start();
        person3.start();
        person4.start();
        person5.start();
        person6.start();
    }
}

class Person extends Thread {
    private Restaurant restaurant;

    public Person(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        restaurant.takeTable();
    }
}

class Restaurant {
    Semaphore table;

    public Restaurant(int tables) {
        this.table = new Semaphore(tables);
    }

    public void takeTable() {
        System.out.println(Thread.currentThread().getName() + " is waiting for a table...");
        try {
            table.acquire();
            System.out.println(Thread.currentThread().getName() + " is eating...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " finished eating...");
            table.release();
        }
    }
}
