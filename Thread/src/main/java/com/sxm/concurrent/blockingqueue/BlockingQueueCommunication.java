package com.sxm.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/13 0013 下午 16:25
 * @since 0.1
 */
public class BlockingQueueCommunication {

    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 5; i++) {
                            business.sub(i);
                        }

                    }
                }
        ).start();

        for (int i = 1; i <= 5; i++) {
            business.main(i);
        }

    }

}

class Business {

    BlockingQueue<Integer> subQueue = new ArrayBlockingQueue<>(1);
    BlockingQueue<Integer> mainQueue = new ArrayBlockingQueue<>(1);

    {
        try {
            mainQueue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sub(int i) {

        try {
            subQueue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int j = 1; j <= 10; j++) {
            System.out.println("sub thread sequence of " + j + ",loop of " + i);
        }
        try {
            mainQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void main(int i) {

        try {
            mainQueue.put(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int j = 1; j <= 10; j++) {
            System.out.println("main thread sequence of " + j + ",loop of " + i);
        }

        try {
            subQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
