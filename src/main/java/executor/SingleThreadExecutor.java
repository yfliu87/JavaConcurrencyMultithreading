package executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutor {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            executor.execute(new Task(i));
        }

        executor.shutdown();

        try {
            if (!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow(); // force shutdown after timeout even if threads have not been executed
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

}
