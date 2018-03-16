package com.sxm.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 只有put方法和take方法有阻塞的功能
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/13 0013 上午 10:56
 * @since 0.1
 */
public class BlockingQueueTest {

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep((long) (Math.random() * 1000));
                            System.out.println(Thread.currentThread().getName() + "准备放数据!");
                            blockingQueue.put(1);
                            System.out.println(Thread.currentThread().getName() + "已经放了数据，" + "队列目前有" + blockingQueue.size() + "个数据");

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();

        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //将此处的睡眠时间分别改为100和1000，观察运行结果
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "准备取数据!");
                        blockingQueue.take();
                        System.out.println(Thread.currentThread().getName() + "已经取走数据，" + "队列目前有" + blockingQueue.size() + "个数据");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
