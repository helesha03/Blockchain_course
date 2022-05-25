package task_set_1;

public class Task2 {

    public static void main(String[] args) {
        int numBites = 8;
        System.out.println("Random key of " + numBites + " bites: " + NBitsKeyUtils.random(numBites));
        int[] keyLengthInBites = {8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096};
        for (int bites : keyLengthInBites) {
            System.out.println("Random key of " + bites + " bites: " + NBitsKeyUtils.toBinString(NBitsKeyUtils.random(bites)));
        }
    }
}
