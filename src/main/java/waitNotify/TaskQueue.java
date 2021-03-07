package waitNotify;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {

    private Queue<Matrix> queue = new LinkedList<>();
    private boolean isEmpty = true;
    private boolean isTerminate = false;
    private static final int CAPACITY = 5;

    public synchronized void add(Matrix matrix) {
        while (queue.size() == CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }

        queue.add(matrix);
        isEmpty = false;
        notify();
    }

    public synchronized Matrix remove() {
        Matrix matrix = null;

        while (isEmpty && !isTerminate) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }

        if (queue.size() == 1) {
            isEmpty = true;
        }

        if (queue.isEmpty() && isTerminate) {
            return null;
        }

        System.out.println("queue size " + queue.size());

        matrix = queue.remove();
        if (queue.size() == CAPACITY - 1) {
            notifyAll();
        }

        return matrix;
    }

    public synchronized void terminate() {
        this.isTerminate = true;
        notifyAll();
    }

}
