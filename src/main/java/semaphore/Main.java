package semaphore;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int numberOfThreads = 100;

        Barrier barrier = new Barrier(10);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            threads.add(new Thread(new Runner(barrier)));
        }

        threads.forEach(t -> t.start());
    }
}
