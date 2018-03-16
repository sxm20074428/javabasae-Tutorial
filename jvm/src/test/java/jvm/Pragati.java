package jvm;

import com.sun.management.OperatingSystemMXBean;
import math.MathUtil;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.util.Random;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/6/30 17:01
 * @since 0.1
 */
public class Pragati {

    @Test
    public void printUsage() {
        System.out.println("** MEMORY DETAILS  **");
        // Print initial memory usage.

        Runtime runtime = Runtime.getRuntime();
        printMemoryUsage(runtime);

    }

    @Test
    public void printUsage2() {
        System.out.println("** MEMORY DETAILS  **");
        // Print initial memory usage.

        Runtime runtime = Runtime.getRuntime();
        printMemoryUsage(runtime);

        byte[] bytes = new byte[1*1024*1024];
        System.out.println("分配了1M空间给数组");

        printMemoryUsage(runtime);

    }

    @Test
    public void printUsage3() {
        System.out.println("** MEMORY DETAILS  **");
        // Print initial memory usage.

        Runtime runtime = Runtime.getRuntime();
        printMemoryUsage(runtime);

        byte[] bytes = new byte[4*1024*1024];
        System.out.println("分配了4M空间给数组");

        printMemoryUsage(runtime);

        System.gc();
        System.out.println("回收内存");

        printMemoryUsage(runtime);


    }
    @Test
    public void printUsage4() {
        System.out.println("** MEMORY DETAILS  **");
        // Print initial memory usage.
        Runtime runtime = Runtime.getRuntime();
        printMemoryUsage(runtime);

        // Allocate a 1 Megabyte and print memory usage
        byte[] bytes = new byte[1024 * 1024];
        printMemoryUsage(runtime);

        bytes = null;
        // Invoke garbage collector to reclaim the allocated memory.
        runtime.gc();

        // Wait 5 seconds to give garbage collector a chance to run
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Total memory will probably be the same as the second printUsage call,
        // but the free memory should be about 1 Megabyte larger if garbage collection kicked in.
        printMemoryUsage(runtime);

    }

    @Test
    public void calcCPUUsage() {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        for (int i = 0; i < 3; i++) {
            long start = System.nanoTime();
            //number of available processors;
            int availableProcessors = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
            Random random = new Random(start);
            int seed = Math.abs(random.nextInt());
            System.out.println("CPU USAGE DETAILS");
            System.out.println("Starting Test with " + availableProcessors + " CPUs and random number:" + seed);
            int primes = 100;
            //
            long currentThreadCpuTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
            start = System.nanoTime();
            while (primes != 0) {
                if (MathUtil.isPrime(seed)) {
                    primes--;
                }
                seed++;
            }
            float cpuPercent = PerformanceMonitor.calcCPUUsage(currentThreadCpuTime, start, availableProcessors);
            System.out.println("CPU USAGE : " + cpuPercent + " % ");
            System.out.println("ProcessCpuLoad: " + operatingSystemMXBean.getProcessCpuLoad());
            System.out.println("SystemCpuLoad: " + operatingSystemMXBean.getSystemCpuLoad());


        }
    }


    @Test
    public void calcCPUUsage2() {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        for (int i = 0; i < 3; i++) {
            long start = System.nanoTime();
            //number of available processors;
            int availableProcessors = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
            Random random = new Random(start);
            int seed = Math.abs(random.nextInt());
            System.out.println("CPU USAGE DETAILS");
            System.out.println("Starting Test with " + availableProcessors + " CPUs and random number:" + seed);
            int primes = 100;
            //
            long processCpuTime = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getProcessCpuTime();

            start = System.nanoTime();
            while (primes != 0) {
                if (MathUtil.isPrime(seed)) {
                    primes--;
                }
                seed++;
            }
            float cpuPercent = PerformanceMonitor.calcCPUUsage2(processCpuTime, start, availableProcessors);
            System.out.println("CPU USAGE : " + cpuPercent + " % ");
            System.out.println("ProcessCpuLoad: " + operatingSystemMXBean.getProcessCpuLoad());
            System.out.println("SystemCpuLoad: " + operatingSystemMXBean.getSystemCpuLoad());
        }
    }

    private static void printMemoryUsage(Runtime runtime) {
        long max, total, free, used;
        max = runtime.maxMemory();
        total = runtime.totalMemory();
        free = runtime.freeMemory();
        used = total - free;
        System.out.println("Max Memory: " + max /1.0/ ByteInterval.BYTES_PER_MB + "MB");
        System.out.println("Total Memory: " + total /1.0 / ByteInterval.BYTES_PER_MB + "MB");
        System.out.println("Memory Used: " + used /1.0 / ByteInterval.BYTES_PER_MB + "MB");
        System.out.println("Memory Free: " + free  /1.0/ ByteInterval.BYTES_PER_MB + "MB");
        System.out.println("Percent Used: " + (used / (double) total) * 100 + "%");
        System.out.println("Percent Free: " + (free / (double) total) * 100 + "%");
    }

}