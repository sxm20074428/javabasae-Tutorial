package math;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/1 15:11
 * @since 0.1
 */
public class MathUtil {
    /**
     * 是否是素数
     *
     * @param
     * @return
     * @author 苏晓蒙
     * @time 2017/6/30 17:09
     * @version 0.1
     * @since 0.1
     */
    public static boolean isPrime(int n) {
        // 2 is the smallest prime
        if (n <= 2) {
            return n == 2;
        }
        // even numbers other than 2 are not prime
        if (n % 2 == 0) {
            return false;
        }
        // check odd divisors from 3
        // to the square root of n
        for (int i = 3, end = (int) java.lang.Math.sqrt(n); i <= end; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
