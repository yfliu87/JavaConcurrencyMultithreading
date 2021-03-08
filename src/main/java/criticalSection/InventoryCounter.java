package criticalSection;

public class InventoryCounter {
    private int items = 0;
    //private AtomicInteger items = new AtomicInteger(0);

    Object lock = new Object();

    public void increment() {
        synchronized (this.lock) {
            items++;
        }
    }

    public void decrement() {
        synchronized (this.lock) {
            items--;
        }
    }

    public int getItems() {
        synchronized (this.lock) {
            return this.items;
        }
    }
}
