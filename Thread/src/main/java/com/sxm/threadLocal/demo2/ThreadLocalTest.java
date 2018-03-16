package com.sxm.threadLocal.demo2;

import java.util.Random;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/9 0009 上午 9:45
 * @since 0.1
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put data :" + data);
                    threadLocal.set(data);
                    new A().get();
                    new B().get();
                }
            }).start();

        }
    }

    static class A {
        public void get() {
            int data = threadLocal.get();
            System.out.println("A from " + Thread.currentThread().getName() + " get data :" + data);

        }
    }

    static class B {
        public void get() {
            int data = threadLocal.get();
            System.out.println("B from " + Thread.currentThread().getName() + " get data :" + data);

        }
    }
}
