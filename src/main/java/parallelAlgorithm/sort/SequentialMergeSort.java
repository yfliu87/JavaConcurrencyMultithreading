package parallelAlgorithm.sort;

public class SequentialMergeSort extends AbstractSort {

    public SequentialMergeSort(int[] values) {
        super(values);
    }

    @Override
    public void sort(int low, int high) {
        if (low >= high) {
            return;
        }

        int middle = (low + high)/2;

        sort(low, middle);
        sort(middle + 1, high);
        merge(low, middle, high);
    }


}
