package com.roman.mysan.concurrency.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get()); // get() waits when task is done and then it returns result
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int i = 0;
            for (; i < 10; i++);
            return i;
        }
    }
}
