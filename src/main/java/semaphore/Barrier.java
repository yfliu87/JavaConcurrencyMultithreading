package semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier {
    private Semaphore semaphore = new Semaphore(0);
    private final int numberOfWorkers;
    private int counter = 0;
    private Lock lock = new ReentrantLock();

    public Barrier(int numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }

    public void barrier() {
        this.lock.lock();
        boolean isLastWorker = false;

        try {
            counter++;

            if (counter == this.numberOfWorkers) {
                isLastWorker = true;
            }
        } finally {
            lock.unlock();
        }

        if (isLastWorker) {
            semaphore.release(this.numberOfWorkers - 1);
        } else {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {

            }
        }
    }
}
