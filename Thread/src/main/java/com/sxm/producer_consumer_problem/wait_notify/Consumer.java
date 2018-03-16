package com.sxm.producer_consumer_problem.wait_notify;

/**
 * 消费者类Consumer继承线程类Thread
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2016/11/17 9:50
 * @since 0.1
 */
public class Consumer extends Thread {

    // 每次消费的产品数量
    private int num;

    // 所在放置的仓库
    private Storage storage;

    // 构造函数，设置仓库
    public Consumer(Storage storage) {
        this.storage = storage;
    }

    // 线程run函数
    public void run() {
        consume(num);
    }

    // 调用仓库Storage的消费函数
    public void consume(int num) {
        storage.consume(num);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}