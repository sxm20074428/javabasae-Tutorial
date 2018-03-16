package com.sxm.thread.traditional;


/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/8 0008 上午 9:59
 * @since 0.1
 */
public class TraditionalThreadSynchronized {

    public static void main(String[] args) {

        new TraditionalThreadSynchronized().init();
    }


    private void init() {

        Outputer outputer = new Outputer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    outputer.output("zhangxiaoxiang");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    outputer.output2("lihuoming");
                }
            }
        }).start();


    }


    static class Outputer {

        public void output(String name) {

            int length = name.length();
            synchronized (this) {
                for (int i = 0; i < length; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }

        public synchronized void output2(String name) {
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }


        //lock 为 Outputer.class
        public static synchronized void output3(String name) {
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

    }
}
