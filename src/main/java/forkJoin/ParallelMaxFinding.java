package forkJoin;

import java.util.concurrent.RecursiveTask;

import static forkJoin.Main.THRESHOLD;

public class ParallelMaxFinding extends RecursiveTask<Integer> {
    private int[] nums;
    private int low;
    private int high;

    public ParallelMaxFinding(int[] nums, int low, int high) {
        this.nums = nums;
        this.low = low;
        this.high = high;
    }

    @Override
    protected Integer compute() {

        if (high - low < THRESHOLD) {
            return sequentialMaxFinding();
        }

        int mid = (low + high)/2;
        ParallelMaxFinding t1 = new ParallelMaxFinding(nums, low, mid);
        ParallelMaxFinding t2 = new ParallelMaxFinding(nums, mid + 1, high);

        invokeAll(t1, t2);

        return Math.max(t1.join(), t2.join());
    }

    private int sequentialMaxFinding() {
        int max = nums[low];

        for (int i = low + 1;  i < high; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        return max;
    }

}
