package com.sxm.threadLocal.demo2;

import java.util.Random;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/9 0009 上午 9:45
 * @since 0.1
 */
public class ThreadLocalTest3 {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put data :" + data);
                    ThreadScopeData threadScopeData = ThreadScopeData.getThreadInstance();
                    threadScopeData.setName("name" + data);
                    threadScopeData.setAge(data);

                    new A().get();
                    new B().get();
                }
            }).start();

        }
    }

    static class A {
        public void get() {

            ThreadScopeData threadScopeData = ThreadScopeData.getThreadInstance();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " getMyData: " + threadScopeData.getName() + "," +
                    threadScopeData.getAge());
        }
    }

    static class B {
        public void get() {
            ThreadScopeData threadScopeData = ThreadScopeData.getThreadInstance();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " getMyData: " + threadScopeData.getName() + "," +
                    threadScopeData.getAge());
        }
    }


}

class ThreadScopeData {

    private static ThreadLocal<ThreadScopeData> threadLocal = new ThreadLocal();
    private String name;
    private int age;

    private ThreadScopeData() {
    }

    public static ThreadScopeData getThreadInstance() {

        ThreadScopeData threadScopeData = threadLocal.get();

        if (threadScopeData == null) {
            threadScopeData = new ThreadScopeData();
            threadLocal.set(threadScopeData);
        }

        return threadScopeData;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
