package readWriteLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int HIGHEST_PRICE = 1000;
    private static final int READER_THREADS = 8;

    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        Random random = new Random();
        for (int i = 0 ; i < 100000; i++) {
            inventory.addItem(random.nextInt(HIGHEST_PRICE));
        }

        Thread writerThread = new WriterThread(inventory, HIGHEST_PRICE);
        writerThread.setDaemon(true);
        writerThread.start();

        List<ReaderThread> readers = new ArrayList<>();

        for (int readerIndex = 0; readerIndex < READER_THREADS; readerIndex++) {
            ReaderThread reader = new ReaderThread(inventory, HIGHEST_PRICE);

            reader.setDaemon(true);
            readers.add(reader);
        }

        long startReadingTime = System.currentTimeMillis();

        readers.forEach(t -> t.start());
        readers.forEach(t -> t.joinThread());

        long endReadingTime = System.currentTimeMillis();

        System.out.println(String.format("Reading took %d ms", endReadingTime - startReadingTime));
    }
}
