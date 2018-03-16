package com.sxm.threadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/9 0009 上午 9:45
 * @since 0.1
 */
public class ThreadScopeShareData {

    private static Map<Thread, Integer> threadIntegerMap = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put data :" + data);
                    threadIntegerMap.put(Thread.currentThread(), data);
                    new A().get();
                    new B().get();
                }
            }).start();

        }
    }

    static class A {
        public void get() {
            int data = threadIntegerMap.get(Thread.currentThread());
            System.out.println("A from " + Thread.currentThread().getName() + " get data :" + data);

        }
    }

    static class B {
        public void get() {
            int data = threadIntegerMap.get(Thread.currentThread());
            System.out.println("B from " + Thread.currentThread().getName() + " get data :" + data);

        }
    }
}
