package com.sxm.multiThread;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/9 0009 上午 11:36
 * @since 0.1
 */
public class MultiThreadShareData {

    public static void main(String[] args) {

        final ShareData shareData = new ShareData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                shareData.decrement();

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                shareData.increment();

            }
        }).start();

    }

}

class ShareData {

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
