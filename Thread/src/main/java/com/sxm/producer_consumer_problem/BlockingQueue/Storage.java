package com.sxm.producer_consumer_problem.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 仓库类Storage实现缓冲区
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2016/11/17 9:37
 * @since 0.1
 */
public class Storage {

    // 仓库最大存储量
    private static final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedBlockingQueue<Object> linkedBlockingQueue = new LinkedBlockingQueue<>(100);

    /**
     * 生产num个产品
     *
     * @author 苏晓蒙
     * @time 2016/11/17 10:02
     * @version 0.1
     * @since 0.1
     */
    public void produce(int num) {

        //如果仓库剩余容量为0
        if (linkedBlockingQueue.size() == MAX_SIZE) {
            System.out.println("【库存量】:" + MAX_SIZE + "/t暂时不能执行生产任务!");
        }
        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i) {
            try {
                // 放入产品，自动阻塞
                linkedBlockingQueue.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【现仓储量为】:" + linkedBlockingQueue.size());
        }
    }

    /**
     * 消费num个产品
     *
     * @author 苏晓蒙
     * @time 2016/11/17 10:13
     * @version 0.1
     * @since 0.1
     */
    public void consume(int num) {

        // 如果仓库存储量不足
        if (linkedBlockingQueue.size() == 0) {
            System.out.println("【库存量】:0/t暂时不能执行消费任务!");
        }

        // 消费条件满足情况下，消费num个产品
        for (int i = 1; i <= num; ++i) {
            try {
                // 消费产品，自动阻塞
                linkedBlockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("【现仓储量为】:" + linkedBlockingQueue.size());
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public LinkedBlockingQueue<Object> getLinkedBlockingQueue() {
        return linkedBlockingQueue;
    }

    public void setLinkedBlockingQueue(LinkedBlockingQueue<Object> linkedBlockingQueue) {
        this.linkedBlockingQueue = linkedBlockingQueue;
    }
}
