package com.roman.mysan.concurrency.threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinFrameworkExample {

    private static final long n = 10_000_000_000L;
    private static final int cores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(cores);
        System.out.println(pool.invoke(new MyFork(0, n)));
    }

    static class MyFork extends RecursiveTask<Long> {
        private long from, to, j = 0;

        public MyFork(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            if ((to - from) <= n/cores) {
                for (long i = from; i < to; i++) {
                    j += i;
                }
            } else { //recursive case
                long middle = from + (to - from) / 2;
                MyFork firstHalf = new MyFork(from, middle);
                firstHalf.fork();
                MyFork secondHalf = new MyFork(middle, to);
                secondHalf.fork();
                //computes result of the second half
                //waits for the first task result
                //adds both results
                return secondHalf.compute() + firstHalf.join();
            }
            return j;
        }
    }
}
