package concurrentCollection;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueue {

    public static void main(String[] args) {
        java.util.concurrent.BlockingQueue<Integer> queue = new PriorityBlockingQueue<Integer>(10);

        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }

    private static class Producer implements Runnable {

        private java.util.concurrent.BlockingQueue<Integer> blockingQueue;

        public Producer(java.util.concurrent.BlockingQueue<Integer> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            try {
                this.blockingQueue.put(23);
                this.blockingQueue.put(12);
                this.blockingQueue.put(34);
                Thread.sleep(1000);
                this.blockingQueue.put(8);
                this.blockingQueue.put(56);
            } catch (InterruptedException e) {

            }
        }
    }

    private static class Consumer implements Runnable {

        private java.util.concurrent.BlockingQueue<Integer> blockingQueue;

        public Consumer(java.util.concurrent.BlockingQueue<Integer> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            try {
                while(!this.blockingQueue.isEmpty()) {
                    System.out.println(this.blockingQueue.take());

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
