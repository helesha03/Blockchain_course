package task_set_1;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;

public class Task3 {

    public static void main(String[] args) {
        int bites = 32;
        BigInteger passwd = NBitsKeyUtils.random(bites);
        System.out.println("Original: " + passwd + ", in hex represenation: " + NBitsKeyUtils.toHexString(passwd));
        Instant start = Instant.now();
        BigInteger res = NBitsKeyUtils.bruteforce(passwd);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Bruteforced: " + res + ", in hex represenation: " + NBitsKeyUtils.toHexString(res) + " time: " + timeElapsed.toMillis() + " ms");
    }
}
