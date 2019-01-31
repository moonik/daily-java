package com.roman.mysan.concurrency.threads;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Experiments {

    public static void main(String[] args) {
        System.out.println(TimeUnit.DAYS.toSeconds(365)); // seconds in 1 year
        System.out.println(ThreadLocalRandom.current().nextInt()); // generates random number
    }
}
