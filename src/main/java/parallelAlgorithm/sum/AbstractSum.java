package parallelAlgorithm.sum;

public abstract class AbstractSum {
    protected int[] values;

    public AbstractSum(int[] values) {
        this.values = values;
    }

    public abstract long sum(int low, int high);

    public abstract long sum(int low, int high, int numOfThreads);
}
