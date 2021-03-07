package readWriteLock;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class Inventory {
    private TreeMap<Integer, Integer> stock = new TreeMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = reentrantReadWriteLock.readLock();
    private Lock writeLock = reentrantReadWriteLock.writeLock();

    public int getNumberOfItemsInPricesRange(int lowerBound, int upperBound) {
        readLock.lock();

        try {
            Integer fromKey = stock.ceilingKey(lowerBound);
            Integer toKey = stock.floorKey(upperBound);

            if (fromKey == null || toKey == null) {
                return 0;
            }

            NavigableMap<Integer, Integer> rangeOfPrices = stock.subMap(fromKey, true, toKey, true);

            return rangeOfPrices.values().stream().collect(Collectors.summingInt(Integer::intValue));
        } finally {
            readLock.unlock();
        }
    }

    public void addItem(int price) {
        writeLock.lock();

        try {
            stock.put(price, stock.getOrDefault(price, 0) + 1);
        } finally {
            writeLock.unlock();
        }
    }

    public void removeItem(int price) {
        writeLock.lock();

        try {
            if (!stock.containsKey(price) || stock.get(price) == 1) {
                stock.remove(price);
            } else {
                stock.put(price, stock.get(price) - 1);
            }
        } finally {
            writeLock.unlock();
        }
    }
}
