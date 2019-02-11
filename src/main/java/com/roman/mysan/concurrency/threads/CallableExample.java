package com.roman.mysan.concurrency.threads;

import java.util.concurrent.*;

public class CallableExample {

    private static void exampleTwo() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future  = executor.submit(new MyCallable());
        executor.shutdown();
        System.out.println(future.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        //System.out.println(futureTask.get()); // get() waits when task is done and then it returns result

        //second example
        exampleTwo();
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
