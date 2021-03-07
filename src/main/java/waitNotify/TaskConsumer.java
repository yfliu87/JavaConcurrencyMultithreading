package waitNotify;

import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

public class TaskConsumer extends Thread {

    private TaskQueue queue;
    private FileWriter fileWriter;
    private int size;

    public TaskConsumer(FileWriter writer, TaskQueue queue, int size) {
        this.fileWriter = writer;
        this.queue = queue;
        this.size = size;
    }

    @Override
    public void run() {
        while (true) {
            Matrix matrix = queue.remove();

            if (matrix == null) {
                System.out.println("No more matrix to read from queue, consumer is terminating...");
                break;
            }

            try {
                saveMatrixToFile(fileWriter, multiplyMatrices(matrix, matrix));
            } catch (IOException e) {}

        }

        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Matrix multiplyMatrices(Matrix matrix1, Matrix matrix2) {
        Matrix result = new Matrix(size, size);
        for (int r = 0; r < size; r++) {
            for (int c = 0 ; c < size; c++) {
                for (int k = 0; k < size; k++) {
                    result.elements[r][c] += matrix1.elements[r][k] * matrix2.elements[k][c];
                }
            }
        }
        return result;
    }

    private void saveMatrixToFile(FileWriter writer, Matrix matrix) throws IOException {
        for (int r = 0; r < size; r++) {
            StringJoiner stringJoiner = new StringJoiner(", ");

            for (int c = 0; c < size; c++) {
                stringJoiner.add(String.format("%.2f", matrix.elements[r][c]));
            }
            fileWriter.write(stringJoiner.toString());
            fileWriter.write('\n');
        }
        fileWriter.write('\n');
    }
}
