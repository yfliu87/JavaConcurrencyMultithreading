package semaphore;

public class Runner implements Runnable {

    private Barrier barrier;

    public Runner(Barrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            task();
        } catch (InterruptedException e) {

        }
    }

    private void task() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " part 1 of the work is done");

        barrier.barrier();

        System.out.println(Thread.currentThread().getName() + " part 2 of the work is done");
    }

}
