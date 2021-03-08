package atomic;

public interface Stack<T> {
    public void push(T value);

    public T pop();

    public int getCount();
}
