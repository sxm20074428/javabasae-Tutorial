package com.sxm.concurrent.CyclicBarrier.demo1;

import java.util.concurrent.CyclicBarrier;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/3/6 12:56
 * @since 0.1
 */
public class Soldier implements Runnable {

    private String name;
    private CyclicBarrier cyclBr;

    public Soldier(String name, CyclicBarrier cyclBr) {
        this.name = name;
        this.cyclBr = cyclBr;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println(this.getName() + "端枪，准备好...");
        try {
            cyclBr.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + "射击>>>>>>");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
