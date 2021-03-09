package semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier {
    private Semaphore semaphore;
    private int semaphorePermitsCount;
    private int counter;
    private Lock lock = new ReentrantLock();

    public Barrier(int semaphorePermitsCount) {
        this.semaphorePermitsCount = semaphorePermitsCount;
        this.semaphore = new Semaphore(this.semaphorePermitsCount);
    }

    public void barrier() {
        this.lock.lock();
        boolean shouldReleaseBuffer = false;

        try {
            counter++;

            if (counter == this.semaphorePermitsCount) {
                shouldReleaseBuffer = true;
                counter = 0;
            }
        } finally {
            lock.unlock();
        }

        if (shouldReleaseBuffer) {
            semaphore.release(this.semaphorePermitsCount - 1); // thread is unblocked after release
            System.out.println("Semaphore released");
        } else {
            try {
                semaphore.acquire(); // thread is not blocked before all permits are acquired
                System.out.println(Thread.currentThread().getName() + " acquired semaphore");
            } catch (InterruptedException e) {

            }
        }
    }
}
