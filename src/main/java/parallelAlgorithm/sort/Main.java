package parallelAlgorithm.sort;

import java.util.Random;

public class Main {

    private static Random random = new Random();

    public static void main(String[] args) throws Throwable {

        int numOfThreads  = Runtime.getRuntime().availableProcessors();

        System.out.println("Number of threads/cores: " + numOfThreads);

        int[] numbers1 = createRandomArray(10000000);
        int[] numbers2 = new int[numbers1.length];

        for(int i = 0; i < numbers1.length; i++) {
            numbers2[i] = numbers1[i];
        }

        ParallelMergeSort parallelSorter = new ParallelMergeSort(numbers1);

        long startTime = System.currentTimeMillis();
        parallelSorter.sort(0, numbers1.length-1, numOfThreads);
        long endTime = System.currentTimeMillis();
        System.out.printf("Time taken for 10 000 000 elements parallel =>  %6d ms \n", endTime - startTime);

        SequentialMergeSort sequentialSorter = new SequentialMergeSort(numbers2);

        startTime = System.currentTimeMillis();
        sequentialSorter.sort(0, numbers2.length-1);
        endTime = System.currentTimeMillis();
        System.out.printf("Time taken for 10 000 000 elements sequential =>  %6d ms \n", endTime - startTime);

    }

    public static int[] createRandomArray(int n) {

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(10000);
        }

        return array;
    }
}
