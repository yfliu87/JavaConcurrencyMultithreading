package concurrentCollection;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueue {

    public static void main(String[] args) {
        java.util.concurrent.BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        new Thread(new Consumer("c1", queue)).start();
        new Thread(new Consumer("c2", queue)).start();
        new Thread(new Consumer("c3", queue)).start();

        new Thread(new Producer("p1", queue)).start();
        new Thread(new Producer("p2", queue)).start();
    }

    private static class Producer implements Runnable {

        private String id;
        private java.util.concurrent.BlockingQueue<String> queue;

        public Producer(String id, java.util.concurrent.BlockingQueue<String> queue) {
            this.id = id;
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true) {
                System.out.println("Producer " + this.id + " producing...");

                this.queue.add(this.id);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {

        private String id;
        private java.util.concurrent.BlockingQueue<String> queue;

        public Consumer(String id, java.util.concurrent.BlockingQueue<String> queue) {
            this.id = id;
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true) {
                System.out.println("Consumer " + this.id + " consuming...");

                try {
                    this.queue.take();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
