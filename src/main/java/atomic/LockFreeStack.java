package atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class LockFreeStack<T> implements Stack<T> {
    private AtomicReference<StackNode<T>> head = new AtomicReference<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void push(T value) {
        StackNode<T> newHeadNode = new StackNode<>(value);

        while(true) {
            StackNode<T> currentHead = head.get();
            newHeadNode.next = currentHead;

            if (head.compareAndSet(currentHead, newHeadNode)) {
                break;
            } else {
                LockSupport.parkNanos(1);
            }
        }
        counter.incrementAndGet();
    }

    @Override
    public T pop() {
        StackNode<T> currentHead = head.get();
        StackNode<T> newHead;

        while(currentHead != null) {
            newHead = currentHead.next;

            if (head.compareAndSet(currentHead, newHead)) {
                break;
            } else {
                LockSupport.parkNanos(1);
                currentHead = head.get();
            }
        }
        counter.incrementAndGet();
        return currentHead != null ? currentHead.value : null;
    }

    @Override
    public int getCount() {
        return this.counter.get();
    }
}
