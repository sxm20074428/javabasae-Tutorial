package jvm;

/**
 * 常用的字节间隔
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/5/23 12:15
 * @since 0.1
 */
public final class ByteInterval {

    /**
     * 1 byte 1 byte.
     */
    private static final int BYTES_PER_BYTE = 1;

    /**
     * 1KB 1024 bytes.
     */
    public static final int BYTES_PER_KB = 1024 * BYTES_PER_BYTE;

    /**
     * 1MB 1024 * 1024 =1048576 bytes.
     */
    public static final int BYTES_PER_MB = 1024 * BYTES_PER_KB;

    /**
     * 1GB 1024 * 1024 * 1024 =1073 741 824 bytes.
     */
    public static final int BYTES_PER_GB = 1024 * BYTES_PER_MB;

    private ByteInterval() {
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }
}
