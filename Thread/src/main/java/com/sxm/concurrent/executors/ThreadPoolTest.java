package com.sxm.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/9 0009 下午 13:58
 * @since 0.1
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //        ExecutorService executorService = Executors.newCachedThreadPool();
        //        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int task = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
                    }
                }
            });
        }

        System.out.println("all of 10 tasks have committed! ");
        executorService.shutdown();

        while (!executorService.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

}
