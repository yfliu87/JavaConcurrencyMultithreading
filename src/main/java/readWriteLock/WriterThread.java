package readWriteLock;

import java.util.Random;

public class WriterThread extends Thread {
    private Inventory inventory;
    private int highest_price;

    public WriterThread(Inventory inventory, int highest_price) {
        this.inventory = inventory;
        this.highest_price = highest_price;
    }

    @Override
    public void run() {
        Random random = new Random();

        while(true) {
            inventory.addItem(random.nextInt(this.highest_price));
            inventory.removeItem(random.nextInt(this.highest_price));

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
        }
    }
}
