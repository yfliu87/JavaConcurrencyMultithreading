package parallelAlgorithm.sort;

public class ParallelMergeSort extends AbstractSort {

    public ParallelMergeSort(int[] values) {
        super(values);
    }

    @Override
    public void sort(int low, int high, int numOfThreads) {
        if (numOfThreads <= 1) {
            sequentialMergeSort(low, high);
            return;
        }

        int middle = (low + high) /2;

        Thread left = sortThread(low, middle, numOfThreads);
        Thread right = sortThread(middle + 1, high, numOfThreads);

        left.start();
        right.start();

        try {
            left.join();
            right.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(low, middle, high);
    }

    public Thread sortThread(int low, int high, int numOfThreads) {
        return new Thread() {

            @Override
            public void run() {
                sort(low, high, numOfThreads/2);
            }
        };
    }

    private void sequentialMergeSort(int low, int high) {
        if (low >= high) {
            return;
        }

        int middle = (low + high)/2;

        sequentialMergeSort(low, middle);
        sequentialMergeSort(middle + 1, high);
        merge(low, middle, high);
    }

}
