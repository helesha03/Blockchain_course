package task_set_1;

import java.math.BigInteger;
import java.util.Random;

public class NBitsKeyUtils {
    public static BigInteger numberOfKeys(int bites) {
        return BigInteger.TWO.pow(bites);
    }

    public static BigInteger random(int bites, int seed) {
        return new BigInteger(bites, new Random(seed));
    }

    public static BigInteger random(int bites) {
        return new BigInteger(bites, new Random());
    }

    public static BigInteger bootforce(BigInteger sample) {
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
