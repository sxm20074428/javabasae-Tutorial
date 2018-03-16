package gc;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/2/25 11:08
 * @since 0.1
 */
public class PrintThread extends Thread {

    public static final long startTime = System.nanoTime();

    @Override
    public void run() {
        try {
            while (true) {
                long t = System.currentTimeMillis() - startTime;
                System.out.println("time:" + t);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PrintThread printThread = new PrintThread();
        printThread.start();
    }
}
