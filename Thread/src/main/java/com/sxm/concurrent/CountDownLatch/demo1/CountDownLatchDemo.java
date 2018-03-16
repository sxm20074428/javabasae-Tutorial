package com.sxm.concurrent.CountDownLatch.demo1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch是一个倒计数的锁存器,构造时传入int参数,该参数就是计数器的初始值，每调用一次countDown()方法，计数器减1,计数器大于0 时，
 * await()方法会阻塞当前进程继续执行,直到计数器减至0时，利用这种特性，可以让主线程等待子线程的结束。
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/1/19 9:48
 * @since 0.1
 */
public class CountDownLatchDemo {

    /**
     * 运动员人数
     */
    private static final int PLAYER_AMOUNT = 5;

    //对于每位运动员，CountDownLatch减1后即结束比赛
    static CountDownLatch beginCountDownLatch = new CountDownLatch(1);

    //对于整个比赛，所有运动员结束后才算结束
    static CountDownLatch endCountDownLatch = new CountDownLatch(PLAYER_AMOUNT);

    public static void main(String[] args) {


        Player[] players = new Player[PLAYER_AMOUNT];

        for (int i = 0; i < PLAYER_AMOUNT; i++) {
            players[i] = new Player("运动员" + i, beginCountDownLatch, endCountDownLatch);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(PLAYER_AMOUNT);

        System.out.println("活动场地部署中...");

        for (Player player : players) {
            //分配线程
            executorService.execute(player);
        }

        Long startTime = System.currentTimeMillis();
        beginCountDownLatch.countDown();
        try {
            //等待end状态变为0，即为比赛结束
            endCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("比赛结束，总耗时：【" + (System.currentTimeMillis() - startTime) / 1000 + "】秒。");
            executorService.shutdown();
        }
    }
}
