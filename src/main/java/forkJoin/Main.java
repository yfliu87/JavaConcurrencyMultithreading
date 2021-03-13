package forkJoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static int size = 200000000;
    public static int THRESHOLD = size / Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        int[] nums = createRandomArray(size);

        long start = System.currentTimeMillis();
        System.out.println(new SequentialMaxFinding(nums).max());
        System.out.println("Sequential max finding takes: " + (System.currentTimeMillis() - start) + " milliseconds");

        start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMaxFinding parallelMaxFinding = new ParallelMaxFinding(nums, 0, size);

        System.out.println(pool.invoke(parallelMaxFinding));
        System.out.println("parallel max finding takes: " + (System.currentTimeMillis() - start) + " milliseconds");
    }

    private static int[] createRandomArray(int size) {
        int[] nums = new int[size];
        Random random = new Random();

        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(1000);
        }
        return nums;
    }
}
