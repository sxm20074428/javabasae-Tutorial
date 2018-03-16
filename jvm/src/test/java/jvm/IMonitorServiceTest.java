package jvm;

public class IMonitorServiceTest {

    public static void main(String[] args) throws Exception {

        IMonitorService monitorService = new MonitorServiceImpl();
        MonitorInfoBean monitorInfoBean = monitorService.getMonitorInfoBean();
        System.out.println("ProcessCpuLoad=" + monitorInfoBean.getProcessCpuLoad() * 100 + "%");
        System.out.println("SystemCpuLoad=" + monitorInfoBean.getSystemCpuLoad() * 100 + "%");

        System.out.println("Java的运行环境版本：" + monitorInfoBean.getJdkVersion());
        System.out.println("Java的安装路径：" + monitorInfoBean.getJavaHome());
        System.out.println("Java的虚拟机实现供应商：" + monitorInfoBean.getJavaVmVendor());


        System.out.println("虚拟机最大可使用内存=" + monitorInfoBean.getMaxJvmMemory());
        System.out.println("虚拟机可使用内存=" + monitorInfoBean.getTotalJvmMemory());
        System.out.println("虚拟机剩余内存=" + monitorInfoBean.getFreeJvmMemory());

        System.out.println("pid: " + monitorService.getPid());
        System.out.println("totalJvmMemory: " + monitorService.getTotalJvmMemoryInMB() + " MB");
        System.out.println("UsedJvmMemoryInMB: " + monitorService.getUsedJvmMemoryInMB() + " MB");
        System.out.println("freeJvmMemory: " + monitorService.getFreeJvmMemoryInMB() + " MB");

        System.out.println("操作系统=" + monitorInfoBean.getOsName());

        System.out.println("总的物理内存=" + monitorInfoBean.getTotalPhysicalMemory());
        System.out.println("已使用的物理内存=" + monitorInfoBean.getUsedPhysicalMemory());
        System.out.println("剩余的物理内存=" + monitorInfoBean.getFreePhysicalMemory());
        System.out.println("线程总数=" + monitorInfoBean.getTotalThread());


        //        System.in.read(); // block the program so that we can do some probing on it
    }
}