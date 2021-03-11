package parallelAlgorithm.sum;

import java.util.ArrayList;
import java.util.List;

public class ParallelSum extends AbstractSum {

    private List<ParallelSumWorker> sums;

    public ParallelSum(int[] values) {
        super(values);

        this.sums = new ArrayList<>();
    }

    @Override
    public long sum(final int low, final int high) {
        return 0;
    }

    @Override
    public long sum(final int low, final int high, final int numOfThreads) {
        int steps = (int) Math.ceil(values.length * 1.0 / numOfThreads);

        for (int i = 0 ; i < numOfThreads; i++) {
            sums.add(new ParallelSumWorker(values, i * steps , (i + 1) * steps));
        }

        sums.forEach(t -> t.start());
        sums.forEach(t -> t.waitForCompletion());

        return sums.stream().mapToLong(t -> t.getResult()).sum();
    }

}
