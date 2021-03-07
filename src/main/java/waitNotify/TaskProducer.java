package waitNotify;

import java.io.FileReader;
import java.util.Scanner;

public class TaskProducer extends Thread {
    private Scanner scanner;
    private TaskQueue queue;
    private int size;

    public TaskProducer(FileReader reader, TaskQueue queue, int size) {
        this.scanner = new Scanner(reader);
        this.queue = queue;
        this.size = size;
    }

    @Override
    public void run() {
        while (true) {
            Matrix matrix = readMatrix();

            if (matrix == null) {
                queue.terminate();
                System.out.println("No more matrix to read. Producer thread is terminating...");
                return;
            }

            queue.add(matrix);
        }
    }

    private Matrix readMatrix() {
        Matrix matrix = new Matrix(size, size);
        for (int r = 0; r < size; r++) {
            if (!scanner.hasNext()) {
                return null;
            }

            String[] line = scanner.nextLine().split(",");
            for (int c = 0; c < size; c++) {
                matrix.elements[r][c] = Float.valueOf(line[c]);
            }
        }

        scanner.nextLine();
        return matrix;
    }
}
