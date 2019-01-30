package com.roman.mysan.concurrency.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class ExecutorsExample {

    public static void executorServiceExample() throws InterruptedException, ExecutionException {
        Runnable runnable = () -> System.out.println("Runnable is running...");
        Callable<String> callable = () -> {System.out.println("Callable is running..."); return "Callable Finished";};

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(runnable);
        System.out.println(executor.submit(callable).get());

        //execute them again
        executor.execute(runnable);
        executor.invokeAll(new ArrayList<>(Arrays.asList(callable)));
        executor.shutdown();
    }

    public static void scheduledExecutorServiceExample() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Runnable runnable = () -> System.out.println("Foooo");
        executorService.schedule(runnable, 3, TimeUnit.SECONDS); // executes task after 3 seconds
        executorService.shutdown();
        //executorService.scheduleAtFixedRate()
    }

    public static void scheduledTaskExample() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Runnable runnable = () -> System.out.println("Foooo");
        executorService.scheduleAtFixedRate(runnable, 0, 3L, TimeUnit.SECONDS); // executes every 3 seconds with initial delay 3 seconds
        //executorService.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //executorServiceExample();
        //scheduledExecutorServiceExample();
        scheduledTaskExample();
    }
}
