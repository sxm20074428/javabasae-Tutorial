package com.sxm.concurrent.executors.monitor;

import java.util.concurrent.*;

public class WorkerPool {

    public static void main(String args[]) throws InterruptedException {

        // RejectedExecutionHandler implementation
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();

        // Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // creating the ThreadPoolExecutor
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);

        // start the monitoring thread
        MonitorRunnable monitorRunnable = new MonitorRunnable(threadPoolExecutor, 3);
        Thread monitorThread = new Thread(monitorRunnable);
        monitorThread.start();

        // submit work to the thread pool
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new WorkerRunnable("cmd" + i));
        }

        Thread.sleep(30000);

        // shut down the pool
        threadPoolExecutor.shutdown();

        // shut down the monitor thread
        Thread.sleep(5000);
        monitorRunnable.shutdown();

    }
}
