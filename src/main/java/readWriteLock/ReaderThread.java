package readWriteLock;

import java.util.Random;

public class ReaderThread extends Thread {
    private Inventory inventory;
    private int highest_price;

    public ReaderThread(Inventory inventory, int highest_price) {
        this.inventory = inventory;
        this.highest_price = highest_price;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < 100000; i++) {
            int upperBound = random.nextInt(this.highest_price);
            int lowerBound = upperBound > 0 ? random.nextInt(upperBound) : 0;

            inventory.getNumberOfItemsInPricesRange(lowerBound, upperBound);
        }
    }

    public void joinThread() {
        try {
            this.join();
        } catch (InterruptedException e) {
            System.out.println("Reader thread interrupted exception");
        }
    }
}
