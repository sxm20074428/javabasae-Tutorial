package jvm;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/6/28 17:01
 * @since 0.1
 */
public interface IMonitorService {

    /**
     * 获得当前的监控对象.
     *
     * @return 返回构造好的监控对象
     */
    MonitorInfoBean getMonitorInfoBean();

    /**
     * 获取当前进程的PID
     *
     * @return
     */
    int getPid();

    /**
     * 获取操作系统名称
     *
     * @return osName
     */
    String getOsName();

    /**
     * 获取jvm 总的内存
     * 单位MB
     *
     * @return
     */
    double getTotalJvmMemoryInMB();

    /**
     * 获取jvm 使用的内存
     * 单位MB
     *
     * @return
     */
    double getUsedJvmMemoryInMB();

    /**
     * 获取jvm 可用内存
     * 单位MB
     *
     * @return
     */
    double getFreeJvmMemoryInMB();

}
