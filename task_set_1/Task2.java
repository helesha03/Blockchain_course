package task_set_1;

public class Task2 {

    public static void main(String[] args) {
        int numBits = 8;
        System.out.println("Random key of " + numBits + " bits: " + NBitsKeyUtils.random(numBits));
        int[] keyLengthInBits = {8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096};
        for (int bits : keyLengthInBits) {
            System.out.println("Random key of " + bits + " bites: " + NBitsKeyUtils.toBinString(NBitsKeyUtils.random(bits)));
        }
    }
}
