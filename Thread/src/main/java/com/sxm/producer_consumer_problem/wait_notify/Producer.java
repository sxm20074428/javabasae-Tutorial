package com.sxm.producer_consumer_problem.wait_notify;

/**
 * 生产者类Producer继承线程类Thread
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2016/11/17 9:47
 * @since 0.1
 */
public class Producer extends Thread {

    // 每次生产的产品数量
    private int num;

    // 所在放置的仓库
    private Storage storage;

    // 构造函数，设置仓库
    public Producer(Storage storage) {
        this.storage = storage;
    }

    // 线程run函数
    public void run() {
        produce(num);
    }

    // 调用仓库Storage的生产函数
    public void produce(int num) {
        storage.produce(num);
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