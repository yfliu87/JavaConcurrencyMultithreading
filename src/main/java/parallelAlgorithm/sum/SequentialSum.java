package parallelAlgorithm.sum;

public class SequentialSum extends AbstractSum {

    public SequentialSum(int[] values) {
        super(values);
    }

    @Override
    public long sum(final int low, final int high) {
        long result = 0;

        for (int i = low; i <= high; i++) {
            result += values[i];
        }

        return result;
    }

    @Override
    public long sum(final int low, final int high, final int numOfThreads) {
        return 0;
    }

}
