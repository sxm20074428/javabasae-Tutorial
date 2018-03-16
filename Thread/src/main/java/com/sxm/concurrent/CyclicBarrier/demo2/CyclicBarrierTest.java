package com.sxm.concurrent.CyclicBarrier.demo2;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/13 0013 上午 9:52
 * @since 0.1
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        //设置3个障碍点
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "即将到达集合地点1，当前已有" + (cyclicBarrier.getNumberWaiting() + 1) + "个已经到达，" + (cyclicBarrier.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                        cyclicBarrier.await();

                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "即将到达集合地点2，当前已有" + (cyclicBarrier.getNumberWaiting() + 1) + "个已经到达，" + (cyclicBarrier.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                        cyclicBarrier.await();
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "即将到达集合地点3，当前已有" + (cyclicBarrier.getNumberWaiting() + 1) + "个已经到达，" + (cyclicBarrier.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            executorService.execute(runnable);


        }

        executorService.shutdown();

    }
}
