package com.sxm.concurrent.CyclicBarrier.demo1;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier  多线程线程协调辅助工具
 * 任务栅栏，同批任务，先到达wait的任务挂起，一直等到全部任务到达指定的wait地点后，才能全部唤醒，继续执行
 * 故事，一个班5个士兵同时端枪射击
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/3/6 10:11
 * @since 0.1
 */
public class CyclicBarrierSample {

    static CyclicBarrier cyclBr = new CyclicBarrier(5, new Runnable() {
        @Override
        public void run() {
            System.out.println("听班长口令，开始射击----");
        }
    });

    public static void main(String args[]) {
        for (int i = 0; i <= 4; i++) {
            new Thread(new Soldier("A班士兵" + i, cyclBr)).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("B班开始准备射击（重复使用CyclicBarrier）************");
        for (int i = 0; i <= 4; i++) {
            new Thread(new Soldier("B班士兵" + i, cyclBr)).start();
        }
    }
}
