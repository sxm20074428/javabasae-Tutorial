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
 * 强制在App ClassLoader或者BootStrap ClassLoader中加载

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
            FileInputStream fis = new FileInputStream(classFile);
            FileChannel fileC = fis.getChannel();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableByteChannel outC = Channels.newChannel(baos);
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (true) {
                int i = fileC.read(buffer);
                if (i == 0 || i == -1) {
                    break;
                }
                buffer.flip();
                outC.write(buffer);
                buffer.clear();
            }
            fis.close();
            return baos.toByteArray();
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
