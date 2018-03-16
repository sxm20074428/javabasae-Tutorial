package com.sxm.concurrent.FutureTask;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTaskExample {

    public static void main(String[] args) {

        MyCallable callable1 = new MyCallable(1000);
        MyCallable callable2 = new MyCallable(2000);

        FutureTask<String> futureTask1 = new FutureTask<>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<>(callable2);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask1);
        executor.execute(futureTask2);

        while (true) {
            try {
                System.out.println("Start Time :" + new Date());
                if (futureTask1.isDone() && futureTask2.isDone()) {
                    System.out.println("Done");
                    // shut down executor service
                    executor.shutdown();
                    return;
                }

                if (!futureTask1.isDone()) {

                    System.out.println("futureTask1 Start Time :" + new Date());

                    // wait indefinitely for future task to complete
                    System.out.println("FutureTask1 output=" + futureTask1.get());

                    System.out.println("futureTask1 End Time :" + new Date());

                }

                System.out.println("Waiting for FutureTask2 to complete:"+ new Date());
                String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
                if (s != null) {
                    System.out.println("FutureTask2 output=" + s);
                    System.out.println("futureTask2 End Time :" + new Date());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                // do nothing
            }
        }

    }

}
