package com.sxm.threadLocal.demo2;

import java.util.Random;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/9 0009 上午 9:45
 * @since 0.1
 */
public class ThreadLocalTest2 {

    private static ThreadLocal<MyThreadScopeData> myThreadScopeDataThreadLocal = new ThreadLocal();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put data :" + data);
                    MyThreadScopeData myThreadScopeData = new MyThreadScopeData();
                    myThreadScopeData.setName("name" + data);
                    myThreadScopeData.setAge(data);
                    myThreadScopeDataThreadLocal.set(myThreadScopeData);

                    new A().get();
                    new B().get();
                }
            }).start();

        }
    }

    static class A {
        public void get() {

            MyThreadScopeData myThreadScopeData = myThreadScopeDataThreadLocal.get();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " getMyData: " + myThreadScopeData.getName() + "," +
                    myThreadScopeData.getAge());
        }
    }

    static class B {
        public void get() {
            MyThreadScopeData myThreadScopeData = myThreadScopeDataThreadLocal.get();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " getMyData: " + myThreadScopeData.getName() + "," +
                    myThreadScopeData.getAge());
        }
    }


}

class MyThreadScopeData {

    private String name;
    private int age;

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
