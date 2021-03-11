package parallelAlgorithm.sort;

public abstract class AbstractSort {

    protected int[] values;
    protected int[] temp;

    public AbstractSort(int[] values) {
        this.values = values;
        this.temp = new int[values.length];
    }

    public abstract void sort(int low, int high);

    public abstract void sort(int low, int high, int numOfThreads);

    protected void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            temp[i] = values[i];
        }

        int i = low, j = middle + 1, k = low;

        while((i <= middle) && (j <= high)) {
            if (temp[i] <= temp[j]) {
                values[k] = temp[i];
                i++;
            } else {
                values[k] = temp[j];
                j++;
            }
            k++;
        }
    }

    public void showResult() {
        for (int i = 0; i < this.values.length; i++) {
            System.out.print(values[i] + ",");
        }
    }
}
