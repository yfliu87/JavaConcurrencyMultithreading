package forkJoin;

public class SequentialMaxFinding {

    private int[] nums;

    public SequentialMaxFinding(int[] nums) {
        this.nums = nums;
    }

    public int max() {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}
