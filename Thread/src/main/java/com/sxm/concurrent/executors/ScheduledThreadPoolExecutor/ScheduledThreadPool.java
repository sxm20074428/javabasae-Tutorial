package com.sxm.concurrent.executors.ScheduledThreadPoolExecutor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        // schedule to run after sometime
        System.out.println("Current Time = " + new Date());
        for (int i = 0; i < 3; i++) {
            Thread.sleep(2000);
            WorkerThread worker = new WorkerThread("do heavy processing");
//             scheduledThreadPool.schedule(worker, 10, TimeUnit.SECONDS);

            // schedule task to execute at fixed rate
            scheduledExecutorService.scheduleAtFixedRate(worker, 0, 10, TimeUnit.SECONDS);

//            scheduledThreadPool.scheduleWithFixedDelay(worker, 0, 1, TimeUnit.SECONDS);

        }

        // add some delay to let some threads spawn by scheduler
        Thread.sleep(60000);

        scheduledExecutorService.shutdown();
        while (!scheduledExecutorService.isTerminated()) {
            // wait for all tasks to finish
        }
        System.out.println("Finished all threads");
    }

}
