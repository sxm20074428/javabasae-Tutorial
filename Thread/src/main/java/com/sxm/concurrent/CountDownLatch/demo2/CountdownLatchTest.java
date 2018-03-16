package com.sxm.concurrent.CountDownLatch.demo2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/13 0013 上午 10:19
 * @since 0.1
 */
public class CountdownLatchTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() + "正准备接受命令");
                        start.await();

                        System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() + "回应命令处理结果");

                        end.countDown();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            executorService.execute(runnable);
        }


        try {
            Thread.sleep((long) (Math.random() * 10000));

            System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");
            start.countDown();
            System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，正在等待结果");

            end.await();
            System.out.println("线程" + Thread.currentThread().getName() + "已收到所有响应结果");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

    }


}
