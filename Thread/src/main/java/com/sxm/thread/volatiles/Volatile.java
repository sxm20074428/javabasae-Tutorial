package com.sxm.thread.volatiles;

import javax.sound.midi.Soundbank;
import java.util.concurrent.TimeUnit;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/6/6 18:07
 * @since 0.1
 */
public class Volatile {

    private static volatile boolean stopThread;

    //    要通过一个线程来终止另外一个线程的场景
    //    通过使用关键字volatile修饰共享变量stopThread，根据volatile的可见性原则可以保证主线程main函数修改了共享变量stopThread状态后对线程th来说是立即可见的，
    // 所以在1秒内线程th将停止循环。
    public static void main(String[] args) throws InterruptedException {

        System.out.println("stopThread:" + stopThread);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopThread) {
                    i++;
//                    System.out.println("i:" + i);
                }
            }
        });
        thread.start();

        long start = System.currentTimeMillis();
        TimeUnit.MILLISECONDS.sleep(1000);
        stopThread = true;
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
