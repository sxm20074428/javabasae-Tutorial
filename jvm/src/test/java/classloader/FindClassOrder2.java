package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 同样配置了参数：-Xbootclasspath/a:D:/java
 * <p>
 * 但是代码中进行了强制在App ClassLoader中加载
 * 结论：在查找类的时候，先在底层的Loader查找，是从下往上的。Apploader能找到，就不会去上层加载器加载
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/2/27 8:58
 * @since 0.1
 */
public class FindClassOrder2 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {

        ClassLoader classLoader = FindClassOrder2.class.getClassLoader();
        byte[] bytes = loadClassBytes("HelloLoader");
        Method md_defineClass = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
        md_defineClass.setAccessible(true);
        md_defineClass.invoke(classLoader, bytes, 0, bytes.length);
        md_defineClass.setAccessible(false);

        HelloLoader loader = new HelloLoader();

        System.out.println(loader.getClass().getClassLoader());
        loader.print();
    }

    private static byte[] loadClassBytes(String className) throws ClassNotFoundException {
        try {
            String classFile = getClassFile(className);
            FileInputStream fileInputStream = new FileInputStream(classFile);
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            WritableByteChannel writableByteChannel = Channels.newChannel(byteArrayOutputStream);
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (true) {
                int i = fileChannel.read(buffer);
                if (i == 0 || i == -1) {
                    break;
                }
                buffer.flip();
                writableByteChannel.write(buffer);
                buffer.clear();
            }
            fileInputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException fnfe) {
            throw new ClassNotFoundException(className);
        }
    }

    private static String getClassFile(String name) {
        //        StringBuffer sb = new StringBuffer("D:\\java\\");

        StringBuffer sb = new StringBuffer("D:\\idea\\jvm\\jvmMonitor\\target\\test-classes\\classloader");
        name = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separator + name);
        return sb.toString();
    }
}
