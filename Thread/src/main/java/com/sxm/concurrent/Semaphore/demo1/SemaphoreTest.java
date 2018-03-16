package com.sxm.concurrent.Semaphore.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore作为同步计数器，作用主要是控制线程可执行的数量。
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        // 线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //下面是一个实例，比如一台服务器，只允许三个客户端同时访问，现在来了10个客户端：
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.print("客户端" + no + "连上了。。。");
                        //获取接下来执行的许可
                        semaphore.acquire();
                        //模拟服务器处理请求的时间
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("客户端" + no + "访问结束。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //释放允许下一个线程访问
                        semaphore.release();
                    }
                }
            };

            executorService.execute(runnable);
        }

        // 退出线程池
        executorService.shutdown();

    }

}
