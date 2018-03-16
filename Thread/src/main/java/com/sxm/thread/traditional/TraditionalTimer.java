package com.sxm.thread.traditional;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/3 10:12
 * @since 0.1
 */
public class TraditionalTimer {

    private static int count = 0;

    public static void main(String[] args) {


        //10秒之后发生
/*        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("bombing!");

            }
        }, 10000);*/

        //10秒之后发，以后每隔3秒发生
     /*   new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("bombing!");

            }
        }, 10000, 3000);*/


        class MyTimerTask extends TimerTask {

            @Override
            public void run() {
                count = (count + 1) % 2;
                System.out.println("bombing!");
                new Timer().schedule(new MyTimerTask(), 2000 + 2000 * count);
            }
        }

        new Timer().schedule(new MyTimerTask(), 2000);


        while (true) {
            System.out.println(new Date().getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
