package waitNotify;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final String INPUT_FILE = "src/main/java/waitNotify/out/matrices";
    private static final String OUTPUT_FILE = "src/main/java/waitNotify/out/matrices_results.txt";
    private static final int N = 10;

    public static void main(String[] args) throws IOException {
        TaskQueue queue = new TaskQueue();
        File inputFile = new File(INPUT_FILE);
        File outputFile = new File(OUTPUT_FILE);

        TaskProducer producer = new TaskProducer(new FileReader(inputFile), queue, N);
        TaskConsumer consumer = new TaskConsumer(new FileWriter(outputFile), queue, N);

        consumer.start();
        producer.start();
    }
}
