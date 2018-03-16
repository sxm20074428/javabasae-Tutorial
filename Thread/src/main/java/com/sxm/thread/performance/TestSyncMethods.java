package com.sxm.thread.performance;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2016/11/17 14:12
 * @since 0.1
 */
public class TestSyncMethods {

    public static void test(int round, int threadNum, CyclicBarrier cyclicBarrier) {

        new SyncTest("Sync", round, threadNum, cyclicBarrier).testTime();
        new LockTest("Lock", round, threadNum, cyclicBarrier).testTime();
        new AtomicTest("Atom", round, threadNum, cyclicBarrier).testTime();

    }

    public static void main(String args[]) {
        for (int i = 0; i < 5; i++) {
            //测试圈数
            int round = 1000 * (i + 1);
            int threadNum = 5 * (i + 1);
            CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum * 2 + 1);
            System.out.println("==========================");
            System.out.println("round:" + round + " thread:" + threadNum);
            test(round, threadNum, cyclicBarrier);
        }
    }
}

class SyncTest extends TestTemplate {

    public SyncTest(String _id, int _round, int _threadNum, CyclicBarrier _cyclicBarrier) {
        super(_id, _round, _threadNum, _cyclicBarrier);
    }

    /**
     * synchronized关键字不在方法签名里面，所以不涉及重载问题
     */
    @Override
    synchronized long getValue() {
        return super.countValue;
    }

    @Override
    synchronized void sumValue() {
        super.countValue += preInit[index++ % round];
    }
}

class LockTest extends TestTemplate {

    ReentrantLock lock = new ReentrantLock();

    public LockTest(String _id, int _round, int _threadNum, CyclicBarrier _cyclicBarrier) {
        super(_id, _round, _threadNum, _cyclicBarrier);
    }

    /**
     * synchronized关键字不在方法签名里面，所以不涉及重载问题
     */
    @Override
    long getValue() {
        try {
            lock.lock();
            return super.countValue;
        } finally {
            lock.unlock();
        }
    }

    @Override
    void sumValue() {
        try {
            lock.lock();
            super.countValue += preInit[index++ % round];
        } finally {
            lock.unlock();

        }
    }
}

class AtomicTest extends TestTemplate {

    public AtomicTest(String _id, int _round, int _threadNum, CyclicBarrier _cyclicBarrier) {
        super(_id, _round, _threadNum, _cyclicBarrier);
    }

    /**
     * synchronized关键字不在方法签名里面，所以不涉及重载问题
     */
    @Override
    long getValue() {
        return super.countValueAtmoic.get();
    }

    @Override
   synchronized void sumValue() {

        super.countValueAtmoic.addAndGet(super.indexAtomic[index++ % round].get());

    }
}

abstract class TestTemplate {

    private String id;
    /**
     * 次数
     */
    protected int round;
    /**
     * 线程数量
     */
    private int threadNum;
    protected long countValue;
    protected AtomicLong countValueAtmoic = new AtomicLong(0);
    protected int[] preInit;
    protected int index;
    protected AtomicInteger[] indexAtomic;
    /**
     * 任务栅栏，同批任务，先到达wait的任务挂起，一直等到全部任务到达指定的wait地点后，才能全部唤醒，继续执行
     */
    private CyclicBarrier cyclicBarrier;

    public TestTemplate(String _id, int _round, int _threadNum, CyclicBarrier _cyclicBarrier) {
        this.id = _id;
        this.round = _round;
        this.threadNum = _threadNum;
        cyclicBarrier = _cyclicBarrier;
        preInit = new int[round];
        indexAtomic = new AtomicInteger[round];
        for (int i = 0; i < preInit.length; i++) {
            preInit[i] = i;
            indexAtomic[i] = new AtomicInteger(i);
        }
    }

    abstract void sumValue();

    /*
     * 对long的操作是非原子的，原子操作只针对32位
     * long是64位，底层操作的时候分2个32位读写，因此不是线程安全
     */
    abstract long getValue();

    public void testTime() {

        ExecutorService se = Executors.newCachedThreadPool();

        long start = System.nanoTime();
        //同时开启2*ThreadNum个数的读写线程
        for (int i = 0; i < threadNum; i++) {
            se.execute(new Runnable() {

                public void run() {
                    for (int i = 0; i < round; i++) {
                        sumValue();
                    }
                    //每个线程执行完同步方法后就等待
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            se.execute(new Runnable() {
                public void run() {
                    getValue();
                    try {
                        //每个线程执行完同步方法后就等待
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            //当前统计线程也wait,所以CyclicBarrier的初始值是threadNum*2+1
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //所有线程执行完成之后，才会跑到这一步
        long duration = System.nanoTime() - start;
        System.out.println(id + " = " + duration + " countValue = " + countValue + " countValueAtmoic = " + countValueAtmoic);
    }
}

