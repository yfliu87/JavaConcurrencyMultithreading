package parallelAlgorithm.sum;

public class ParallelSumWorker extends Thread {

    private int[] values;
    private int low;
    private int high;
    private long sum;

    public ParallelSumWorker(int[] values, int low, int high) {
        this.values = values;
        this.low = low;
        this.high = high;
    }

    @Override
    public void run() {
        sum = 0;

        for (int i = low; i < high && i < values.length; i++) {
            sum += values[i];
        }
    }

    public void waitForCompletion() {
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getResult() {
        return this.sum;
    }
}
