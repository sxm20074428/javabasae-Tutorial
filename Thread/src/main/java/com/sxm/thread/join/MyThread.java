package com.sxm.thread.join;

import java.util.ArrayList;
import java.util.List;

/**
 * 主线程等待子线程,join()方法会阻塞主线程继续向下执行
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/3/17 13:49
 * @since 0.1
 */
public class MyThread extends Thread {

    public MyThread(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " staring...");

        System.out.println(this.getName() + " end...");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("main thread starting...");

        List<MyThread> list = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            MyThread myThread = new MyThread("Thread " + i);
            myThread.start();
            list.add(myThread);
        }

        try {
            for (MyThread my : list) {
                my.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread end...");

    }

}
