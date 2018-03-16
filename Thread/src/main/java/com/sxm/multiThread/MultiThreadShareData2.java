package com.sxm.multiThread;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/9 0009 上午 11:36
 * @since 0.1
 */
public class MultiThreadShareData2 {

    public static void main(String[] args) {

        ShareData1 shareData1 = new ShareData1();
        new Thread(new MyRunnable1(shareData1)).start();
        new Thread(new MyRunnable2(shareData1)).start();

    }

}

class MyRunnable1 implements Runnable{

    private ShareData1 data1;
    public MyRunnable1(ShareData1 data1){
        this.data1 = data1;
    }
    public void run() {
        data1.decrement();

    }
}

class MyRunnable2 implements Runnable{
    private ShareData1 data1;
    public MyRunnable2(ShareData1 data1){
        this.data1 = data1;
    }
    public void run() {
        data1.increment();
    }
}

class ShareData1 {

    private int j = 0;

    public synchronized void increment() {
        j++;
        System.out.println("j = " + j);
    }

    public synchronized void decrement() {
        j--;
        System.out.println("j = " + j);

    }
}
