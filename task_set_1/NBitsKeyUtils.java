package task_set_1;

import java.math.BigInteger;
import java.util.Random;

public class NBitsKeyUtils {
    public static BigInteger numberOfKeys(int bits) {
        return BigInteger.TWO.pow(bits);
    }

    public static BigInteger random(int bits, int seed) {
        return new BigInteger(bits, new Random(seed));
    }

    public static BigInteger random(int bits) {
        return new BigInteger(bits, new Random());
    }

    public static BigInteger bruteforce(BigInteger sample) {
        BigInteger res = BigInteger.ZERO;
        while(res.compareTo(sample) != 0) {
            res = res.add(BigInteger.ONE);
        }
        return res;
    }

    public static String toHexString(BigInteger key) {
        return key.toString(16);
    }

    public static String toBinString(BigInteger key) {
        return key.toString(2);
    }
}
