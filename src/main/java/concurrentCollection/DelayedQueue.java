package concurrentCollection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedQueue {

    public static void main(String[] args) {

        BlockingQueue<DelayedWorker> queue = new java.util.concurrent.DelayQueue<>();

        try {
            queue.put(new DelayedWorker(1000, "message 1"));
            queue.put(new DelayedWorker(6000, "message 2"));
            queue.put(new DelayedWorker(3000, "message 3"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class DelayedWorker implements Delayed {

        private long duration;
        private String message;

        public DelayedWorker(long duration, String message) {
            this.duration = System.currentTimeMillis() + duration;
            this.message = message;
        }

        public long getDuration() {
            return this.duration;
        }

        @Override
        public long getDelay(final TimeUnit unit) {
            return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(final Delayed other) {
            if (this.duration < ((DelayedWorker) other).getDuration()) {
                return -1;
            } else if (this.duration > ((DelayedWorker) other).getDuration()) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return this.message;
        }
    }
}
