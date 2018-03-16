package jvm;

import com.sun.management.OperatingSystemMXBean;
import org.junit.Test;

import java.lang.management.ManagementFactory;

/**
 * In JDK 1.7, you can get system CPU and memory usage via com.sun.management.OperatingSystemMXBean. This is different than java.lang.management.OperatingSystemMXBean
 * but you need to cast the java.lang.management.OperatingSystemMXBean to a com.sun.management.OperatingSystemMXBean.
 * This works on Windows and Linux
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/6/30 14:02
 * @since 0.1
 */
public class PerformanceMonitor {

    /**
     * To get the percentage of CPU used, all you need is some simple maths
     *
     * @param
     * @return
     * @author 苏晓蒙
     * @time 2017/6/30 18:03
     * @version 0.1
     * @since 0.1
     */
    public static float calcCPUUsage(long cpuStartTime, long startTime, int availableProcessors) {
        long endTime = System.nanoTime();
        long totalAvailCPUTime = availableProcessors * (endTime - startTime);
        long totalUsedCPUTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - cpuStartTime;

        float per = totalUsedCPUTime * 100 / totalAvailCPUTime;
        return per;
    }

    /**
     * To get the percentage of CPU used, all you need is some simple maths
     *
     * @param
     * @return
     * @author 苏晓蒙
     * @time 2017/6/30 18:03
     * @version 0.1
     * @since 0.1
     */
    public static float calcCPUUsage2(long processCpuTime, long startTime, int availableProcessors) {
        long endTime = System.nanoTime();
        long totalAvailCPUTime = availableProcessors * (endTime - startTime);
        long lastProcessCpuTime = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getProcessCpuTime();
        long totalUsedCPUTime = lastProcessCpuTime - processCpuTime;

        float per = totalUsedCPUTime * 100 / totalAvailCPUTime;
        return per;
    }


    /**
     * In JDK 1.7, you can get system CPU and memory usage via com.sun.management.OperatingSystemMXBean
     *
     * @param
     * @return
     * @author 苏晓蒙
     * @time 2017/6/30 16:35
     * @version 0.1
     * @since 0.1
     */
    @Test
    public void test() {

        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        //the CPU time used by the process on which the Java virtual machine is running in nanoseconds
        System.out.println("ProcessCpuTime: " + operatingSystemMXBean.getProcessCpuTime());

        //the "recent cpu usage" for the Java Virtual Machine process,from 0.0-1.0
        System.out.println("ProcessCpuLoad: " + operatingSystemMXBean.getProcessCpuLoad());

        //the "recent cpu usage" for the whole system,from 0.0-1.0
        System.out.println("SystemCpuLoad: " + operatingSystemMXBean.getSystemCpuLoad());

    }

}
