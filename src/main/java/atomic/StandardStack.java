package atomic;

public class StandardStack<T> implements Stack<T> {

    private StackNode<T> head;
    private int counter = 0;

    @Override
    public synchronized void push(T value) {
        StackNode<T> newHead = new StackNode<>(value);
        newHead.next = head;
        head = newHead;
        counter++;
    }

    @Override
    public synchronized T pop() {
        if (head == null) {
            counter++;
            return null;
        }

        T value = head.value;
        head = head.next;
        counter++;
        return value;
    }

    @Override
    public int getCount() {
        return this.counter;
    }
}
