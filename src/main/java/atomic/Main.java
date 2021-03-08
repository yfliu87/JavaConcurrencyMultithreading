package atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int PUSH_THREAD_COUNT = 2;
    private static final int POP_THREAD_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {

        Stack<Integer> standardStack = new StandardStack<>();
        Stack<Integer> lockFreeStack = new LockFreeStack<>();

        benchmarkStack(standardStack);
        benchmarkStack(lockFreeStack);
    }

    private static void benchmarkStack(Stack<Integer> stack) throws InterruptedException {

        Random random = new Random();

        for (int i = 0 ; i < 10000; i++) {
            stack.push(random.nextInt());
        }

        List<Thread> threads = new ArrayList<>();
        for (int i = 0 ; i < PUSH_THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> {
                while(true) {
                    stack.push(random.nextInt());
                }
            });

            thread.setDaemon(true);
            threads.add(thread);
        }

        for (int i = 0; i < POP_THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> {
                while(true) {
                    stack.pop();
                }
            });

            thread.setDaemon(true);
            threads.add(thread);
        }

        threads.forEach(t -> t.start());

        Thread.sleep(10000);

        System.out.println(String.format("%,d operations were performed in 10 seconds ", stack.getCount()));
    }
}
