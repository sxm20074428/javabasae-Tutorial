package jvm;

import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Properties;

/**
 * 获取系统信息的业务逻辑实现类.
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/6/28 17:10
 * @since 0.1
 */
public class MonitorServiceImpl implements IMonitorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorServiceImpl.class);

    private static Runtime runtime = Runtime.getRuntime();
    private static OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private static Properties properties = System.getProperties();
    //可以设置长些，防止读到运行此次系统检查时的cpu占用率，就不准了
    private static final int CPUTIME = 5000;
    private static final int PERCENT = 100;
    private static final int FAULTLENGTH = 10;
    private static String linuxVersion = null;

    /**
     * 获得当前的监控对象.
     *
     * @return 返回构造好的监控对象
     * @throws Exception
     * @author GuoHuang
     */
    public MonitorInfoBean getMonitorInfoBean() {
        // 构造返回对象
        MonitorInfoBean monitorInfoBean = new MonitorInfoBean();

        //设置jvm的内存
        monitorInfoBean.setTotalJvmMemory(runtime.totalMemory());
        monitorInfoBean.setFreeJvmMemory(runtime.freeMemory());
        monitorInfoBean.setMaxJvmMemory(runtime.maxMemory());

        //设置系统物理内存
        monitorInfoBean.setTotalPhysicalMemory(operatingSystemMXBean.getTotalPhysicalMemorySize());
        monitorInfoBean.setFreePhysicalMemory(operatingSystemMXBean.getFreePhysicalMemorySize());
        monitorInfoBean.setUsedPhysicalMemory(operatingSystemMXBean.getTotalPhysicalMemorySize() - operatingSystemMXBean.getFreePhysicalMemorySize());

        // 操作系统名称
        String osName = properties.getProperty("os.name");
        monitorInfoBean.setOsName(osName);
        monitorInfoBean.setJavaHome(properties.getProperty("java.home"));
        monitorInfoBean.setJdkVersion(properties.getProperty("java.version"));
        monitorInfoBean.setJavaVmVendor(properties.getProperty("java.vm.vendor"));

        // 获得线程总数
        ThreadGroup threadGroup;
        for (threadGroup = Thread.currentThread().getThreadGroup(); threadGroup.getParent() != null; threadGroup = threadGroup.getParent())
            ;
        monitorInfoBean.setTotalThread(threadGroup.activeCount());

        // cpu负载
        monitorInfoBean.setProcessCpuLoad(operatingSystemMXBean.getProcessCpuLoad());
        monitorInfoBean.setSystemCpuLoad(operatingSystemMXBean.getSystemCpuLoad());

        return monitorInfoBean;
    }

    @Override
    public int getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName(); // format: "pid@hostname"
        LOGGER.debug("name:" + name);
        try {
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        } catch (Exception e) {
            return -1;
        }
    }

    public String getOsName() {
        return System.getProperty("os.name");
    }

    @Override
    public double getTotalJvmMemoryInMB() {
        return runtime.totalMemory() /1.0/ ByteInterval.BYTES_PER_MB;
    }

    @Override
    public double getUsedJvmMemoryInMB() {
        return (runtime.totalMemory() - runtime.freeMemory())/1.0/ ByteInterval.BYTES_PER_MB;
    }

    @Override
    public double getFreeJvmMemoryInMB() {
        return runtime.freeMemory() /1.0/ ByteInterval.BYTES_PER_MB;
    }

}


