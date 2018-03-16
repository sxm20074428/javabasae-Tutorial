package com.sxm.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/12 0012 下午 14:32
 * @since 0.1
 */
public class BoundedBuffer {

    /**
     * 缓冲池容纳100个对象
     */
    private Object[] items = new Object[100];
    /**
     * 目前缓冲池中的数量
     */
    private int count;
    /**
     * 放入的索引
     */
    private int putIndex;
    /**
     * 取出的索引
     */
    private int takeIndex;

    private Lock lock = new ReentrantLock();

    Condition notFull = lock.newCondition();//不满了，可以存数据
    Condition notEmpty = lock.newCondition();//不为空，即有数据

    public void put(Object object) throws InterruptedException {

        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }

            items[putIndex] = object;
            if (putIndex == items.length) {
                putIndex = 0;
            }
            count++;
            notEmpty.signal();

        } finally {
            lock.unlock();

        }
    }


    public Object take() throws InterruptedException {

        lock.lock();
        try {

            while (count == 0) {
                notEmpty.await();
            }

            Object obj = items[takeIndex];
            if (++takeIndex == items.length) {
                takeIndex = 0;
            }

            --count;
            notFull.signal();
            return obj;
        } finally {
            lock.unlock();
        }
    }
}
