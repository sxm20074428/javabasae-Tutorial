package com.sxm.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/10 0010 下午 12:47
 * @since 0.1
 */
public class LockTest {


    public static void main(String[] args) {

        new LockTest().init();
    }


    private void init() {

        Outputer outputer = new Outputer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    outputer.output("zhangxiaoxiang");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    outputer.output("lihuoming");
                }
            }
        }).start();


    }


    static class Outputer {

        Lock lock = new ReentrantLock();

        public void output(String name) {

            int length = name.length();
            lock.lock();
            try {
                for (int i = 0; i < length; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }

    }
}

