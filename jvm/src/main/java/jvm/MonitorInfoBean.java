package jvm;

/**
 * 监控的模型
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/6/28 17:00
 * @since 0.1
 */
public class MonitorInfoBean {

    /**
     * java.version
     */
    private String jdkVersion;
    /**
     * java.home
     */
    private String javaHome;
    /**
     * java.vm.vendor
     * Java的虚拟机实现供应商
     */
    private String javaVmVendor;
    /**
     * 虚拟机可使用内存总量.
     * measured in bytes.
     */
    private long totalJvmMemory;
    /**
     * 虚拟机中的空闲内存量.
     * measured in bytes.
     */
    private long freeJvmMemory;
    /**
     * 虚拟机最大可使用内存量.
     * measured in bytes.
     */
    private long maxJvmMemory;
    /**
     * 操作系统名称.
     */
    private String osName;
    /**
     * 总的物理内存.
     * measured in bytes.
     */
    private long totalPhysicalMemory;
    /**
     * 剩余的物理内存.
     * measured in bytes.
     */
    private long freePhysicalMemory;
    /**
     * 已使用的物理内存.
     * measured in bytes.
     */
    private long usedPhysicalMemory;
    /**
     * 线程总数.
     */
    private int totalThread;
    /**
     * Java Virtual Machine process cpu usage
     */
    private double processCpuLoad;

    /**
     * whole system cpu usage
     */
    private double systemCpuLoad;

    public String getJdkVersion() {
        return jdkVersion;
    }

    public void setJdkVersion(String jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public void setJavaHome(String javaHome) {
        this.javaHome = javaHome;
    }

    public String getJavaVmVendor() {
        return javaVmVendor;
    }

    public void setJavaVmVendor(String javaVmVendor) {
        this.javaVmVendor = javaVmVendor;
    }

    public long getTotalJvmMemory() {
        return totalJvmMemory;
    }

    public void setTotalJvmMemory(long totalJvmMemory) {
        this.totalJvmMemory = totalJvmMemory;
    }

    public long getFreeJvmMemory() {
        return freeJvmMemory;
    }

    public void setFreeJvmMemory(long freeJvmMemory) {
        this.freeJvmMemory = freeJvmMemory;
    }

    public long getMaxJvmMemory() {
        return maxJvmMemory;
    }

    public void setMaxJvmMemory(long maxJvmMemory) {
        this.maxJvmMemory = maxJvmMemory;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public long getTotalPhysicalMemory() {
        return totalPhysicalMemory;
    }

    public void setTotalPhysicalMemory(long totalPhysicalMemory) {
        this.totalPhysicalMemory = totalPhysicalMemory;
    }

    public long getFreePhysicalMemory() {
        return freePhysicalMemory;
    }

    public void setFreePhysicalMemory(long freePhysicalMemory) {
        this.freePhysicalMemory = freePhysicalMemory;
    }

    public long getUsedPhysicalMemory() {
        return usedPhysicalMemory;
    }

    public void setUsedPhysicalMemory(long usedPhysicalMemory) {
        this.usedPhysicalMemory = usedPhysicalMemory;
    }

    public int getTotalThread() {
        return totalThread;
    }

    public void setTotalThread(int totalThread) {
        this.totalThread = totalThread;
    }

    public double getProcessCpuLoad() {
        return processCpuLoad;
    }

    public void setProcessCpuLoad(double processCpuLoad) {
        this.processCpuLoad = processCpuLoad;
    }

    public double getSystemCpuLoad() {
        return systemCpuLoad;
    }

    public void setSystemCpuLoad(double systemCpuLoad) {
        this.systemCpuLoad = systemCpuLoad;
    }

}
