package jvm;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Properties;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/6/28 16:40
 * @since 0.1
 */
public class SystemInfo {

    public static void main(String[] args) {

        //获取当前的系统属性
        Properties properties = System.getProperties();

        //将属性列表输出
        properties.list(System.out);

        System.out.println("Java的运行环境版本：" + properties.getProperty("java.version"));
        System.out.println("Java的运行环境供应商：" + properties.getProperty("java.vendor"));
        System.out.println("Java供应商的URL：" + properties.getProperty("java.vendor.url"));
        System.out.println("Java的安装路径：" + properties.getProperty("java.home"));
        System.out.println("Java的虚拟机规范版本：" + properties.getProperty("java.vm.specification.version"));
        System.out.println("Java的虚拟机规范供应商：" + properties.getProperty("java.vm.specification.vendor"));
        System.out.println("Java的虚拟机规范名称：" + properties.getProperty("java.vm.specification.name"));
        System.out.println("Java的虚拟机实现版本：" + properties.getProperty("java.vm.version"));
        System.out.println("Java的虚拟机实现供应商：" + properties.getProperty("java.vm.vendor"));
        System.out.println("Java的虚拟机实现名称：" + properties.getProperty("java.vm.name"));
        System.out.println("Java运行时环境规范版本：" + properties.getProperty("java.specification.version"));
        System.out.println("Java运行时环境规范供应商：" + properties.getProperty("java.specification.vendor"));
        System.out.println("Java运行时环境规范名称：" + properties.getProperty("java.specification.name"));
        System.out.println("Java的类格式版本号：" + properties.getProperty("java.class.version"));
        System.out.println("Java的类路径：" + properties.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表：" + properties.getProperty("java.library.path"));
        System.out.println("默认的临时文件路径：" + properties.getProperty("java.io.tmpdir"));
        System.out.println("一个或多个扩展目录的路径：" + properties.getProperty("java.ext.dirs"));
        System.out.println("操作系统的名称：" + properties.getProperty("os.name"));
        System.out.println("操作系统的构架：" + properties.getProperty("os.arch"));
        System.out.println("操作系统的版本：" + properties.getProperty("os.version"));
        System.out.println("文件分隔符：" + properties.getProperty("file.separator"));  //在 unix 系统中是＂／＂
        System.out.println("路径分隔符：" + properties.getProperty("path.separator"));  //在 unix 系统中是＂:＂
        System.out.println("行分隔符：" + properties.getProperty("line.separator"));  //在 unix 系统中是＂/n＂
        System.out.println("用户的账户名称：" + properties.getProperty("user.name"));
        System.out.println("用户的主目录：" + properties.getProperty("user.home"));

        //获取当前运行时的实例,
        Runtime runtime = Runtime.getRuntime();

        // 获取当前电脑CPU数量
        System.out.println("CPU个数:" + runtime.availableProcessors());

        //获取java虚拟机中的内存总量
        System.out.println("虚拟机可使用内存总量:" + runtime.totalMemory());

        //获取java虚拟机中的空闲内存量
        System.out.println("虚拟机空闲内存量:" + runtime.freeMemory());

        System.out.println("Percent Used: " + ((double) (runtime.totalMemory() - runtime.freeMemory()) / (double) runtime.totalMemory()) * 100 + "%");
        System.out.println("Percent Free: " + ((double) runtime.freeMemory() / (double) runtime.totalMemory()) * 100 + "%");

          /* This will return Long.MAX_VALUE if there is no preset limit */
        //获取java虚拟机试图可使用最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
         /* Maximum amount of memory the JVM will attempt to use */
        System.out.println("Maximum memory (bytes): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

        // The java.lang.management package does give you a whole lot more info than Runtime
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage(); //椎内存使用情况
        long totalMemorySize = memoryUsage.getInit(); //初始的总内存
        long maxMemorySize = memoryUsage.getMax(); //最大可用内存
        long usedMemorySize = memoryUsage.getUsed(); //已使用的内存
        System.out.println("totalMemorySize = " + totalMemorySize);
        System.out.println("maxMemorySize = " + maxMemorySize);
        System.out.println("usedMemorySize = " + usedMemorySize);



        // Get a list of all filesystem roots on this system
        File[] roots = File.listRoots();
        // For each filesystem root, print some info
        for (File root : roots) {
            System.out.println("File system root: " + root.getAbsolutePath());
            System.out.println("Total space (bytes): " + root.getTotalSpace());
            System.out.println("Free space (bytes): " + root.getFreeSpace());
            System.out.println("Usable space (bytes): " + root.getUsableSpace());
        }
    }
}
