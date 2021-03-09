package executor;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    private int taskId;

    public Task(int i) {
        this.taskId = i;
    }

    @Override
    public void run() {
        System.out.println("Task with id " + this.taskId + " is working - thread id: " + Thread.currentThread().getName());

        long duration = (long) (Math.random() * 5);

        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

