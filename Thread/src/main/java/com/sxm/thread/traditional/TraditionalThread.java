package com.sxm.thread.traditional;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/2 14:46
 * @since 0.1
 */
public class TraditionalThread {

    public static void main(String[] args) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("1:" + Thread.currentThread().getName());
                    System.out.println("2:" + this.getName());
                }

            }
        };

        thread.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("3:" + Thread.currentThread().getName());

                }

            }
        });
        thread2.start();


        new Thread(
                new Runnable() {
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("runnable :" + Thread.currentThread().getName());

                        }
                    }
                }
        ) {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread :" + Thread.currentThread().getName());

                }
            }
        }.start();
    }
}
