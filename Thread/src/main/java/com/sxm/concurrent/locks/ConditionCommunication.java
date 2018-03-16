package com.sxm.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/8 0008 上午 11:10
 * @since 0.1
 */
public class ConditionCommunication {

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

    private boolean bShouldSub = true;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void sub(int i) {
        lock.lock();
        try {
            //while 可以防止伪唤醒
            while (!bShouldSub) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int j = 1; j <= 10; j++) {
                System.out.println("sub thread sequence of " + j + ",loop of " + i);
            }
            bShouldSub = false;
            condition.signal();

        } finally {
            lock.unlock();
        }


    }

    public void main(int i) {
        lock.lock();
        try {

            while (bShouldSub) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int j = 1; j <= 10; j++) {
                System.out.println("main thread sequence of " + j + ",loop of " + i);
            }

            bShouldSub = true;
            condition.signal();
        } finally {
            lock.unlock();
        }

    }
}
