package concurrentCollection;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrier {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        java.util.concurrent.CyclicBarrier barrier = new java.util.concurrent.CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("All barriers have been cleared ...");
            }
        });

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Worker(i, barrier));
        }

        executorService.shutdown();
    }

    private static class Worker implements Runnable {

        private int id;
        private java.util.concurrent.CyclicBarrier barrier;

        public Worker(int id, java.util.concurrent.CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("Thread " + this.id + " waits on barrier...");

            try {
                Thread.sleep(1000);

                this.barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("Thread " + this.id + " passes barrier...");
        }
    }
}
