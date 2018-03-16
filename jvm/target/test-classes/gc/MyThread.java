package gc;

import java.util.HashMap;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/2/25 11:22
 * @since 0.1
 */
public class MyThread extends Thread {

    HashMap<Long, byte[]> hashMap = new HashMap<>();

    @Override
    public void run() {

        try {

            while (true) {

                if (hashMap.size() * 512 / 1024 / 1024 >= 450) {
                    System.out.println("=====准备清理=====:" + hashMap.size());
                    hashMap.clear();
                }

                for (int i = 0; i < 1024; i++) {
                    hashMap.put(System.nanoTime(), new byte[512]);

                }

                Thread.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
