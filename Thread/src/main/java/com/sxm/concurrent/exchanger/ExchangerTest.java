package com.sxm.concurrent.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/13 0013 上午 10:31
 * @since 0.1
 */
public class ExchangerTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Exchanger exchanger = new Exchanger();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    String data1 = "zxx";
                    System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data1 + "换出去");
                    Thread.sleep((long) (Math.random() * 10000));

                    Object data2 = exchanger.exchange(data1);
                    System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为" + data2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    String data1 = "lhm";
                    System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data1 + "换出去");
                    Thread.sleep((long) (Math.random() * 10000));

                    Object data2 = exchanger.exchange(data1);
                    System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为" + data2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
