package com.sxm.concurrent.CountDownLatch.demo1;

import java.util.concurrent.CountDownLatch;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/1/19 9:51
 * @since 0.1
 */
public class Player implements Runnable {

    /**
     * 运动员名字
     */
    private String name;
    /**
     * 开始起跑的口令
     */
    private CountDownLatch beginCuntDownLatch;
    /**
     * 比赛结束
     */
    private CountDownLatch endCountDownLatch;

    public Player(String name, CountDownLatch beginCuntDownLatch, CountDownLatch endCountDownLatch) {

        this.name = name;
        this.beginCuntDownLatch = beginCuntDownLatch;
        this.endCountDownLatch = endCountDownLatch;
    }

    @Override
    public void run() {

        System.out.println(this.name + "正在做准备...");

        try {
            //等待beginCuntDownLatch的状态为0
            beginCuntDownLatch.await();

            System.out.println(this.name + "听到口令，开始跑了。。。");

            //随机分配时间，即运动员完成时间
            Thread.sleep((long) (Math.random() * 5000));
            System.out.println("Play" + name + " arrived.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //使endCountDownLatch状态减1，最终减至0
            endCountDownLatch.countDown();
        }
    }
}
