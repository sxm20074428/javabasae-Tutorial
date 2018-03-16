package gc;

/**
 * 可触及性
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/2/25 10:50
 * @since 0.1
 */
public class CanReliveObj {

    public static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj = this;
    }

    @Override
    public String toString() {
        return "I am CanReliveObj";
    }


    public static void main(String[] args) throws InterruptedException {

        obj = new CanReliveObj();
        obj = null; //可复活
        System.gc();

        Thread.sleep(1000);
        if (obj == null) {
            System.out.println("obj is null");

        } else {
            System.out.println("obj is 可用");
        }

        System.out.println("第二次 gc");
        obj = null;//不可以复活
        System.gc();

        if (obj == null) {
            System.out.println("obj is null");

        } else {
            System.out.println("obj 可用");
        }

    }

}
