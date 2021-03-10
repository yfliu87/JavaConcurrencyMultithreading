package concurrentCollection;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Latch {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Worker(i, latch));
        }

        latch.await();

        System.out.println("Count down finished...");

        executorService.shutdown();
    }

    private static class Worker implements Runnable {

        private int id;
        private CountDownLatch latch;

        public Worker(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("Thread " + this.id + " count down...");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.latch.countDown();
        }

    }
}
