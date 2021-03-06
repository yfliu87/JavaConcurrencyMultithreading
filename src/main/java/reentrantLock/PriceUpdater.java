package reentrantLock;

import java.util.Random;

public class PriceUpdater extends Thread {
    private PricesContainer pricesContainer;
    private Random random = new Random();

    public PriceUpdater(PricesContainer container) {
        this.pricesContainer = container;
    }

    @Override
    public void run() {
        while(true) {
            this.pricesContainer.getLockObject().lock();

            try {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }

                pricesContainer.setBitcoinPrice(random.nextInt(50000));
                pricesContainer.setEtherPrice(random.nextInt(2000));
                pricesContainer.setLitecoinPrice(random.nextInt(10000));
                pricesContainer.setBitcoinCashPrice(random.nextInt(5000));
                pricesContainer.setRipplePrice(random.nextDouble());
            } finally {
                this.pricesContainer.getLockObject().unlock();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}
