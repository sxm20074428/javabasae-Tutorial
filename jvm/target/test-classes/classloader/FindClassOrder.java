package classloader;

/**
 * 类加载是从上往下的
 * -Xbootclasspath/a:D:/java
 * 注意：package要建立对应的文件夹，实际位置：D:\java\classloader
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/2/27 8:58
 * @since 0.1
 */
public class FindClassOrder {

    public static void main(String[] args) {

        HelloLoader helloLoader = new HelloLoader();
        helloLoader.print();
    }

}
