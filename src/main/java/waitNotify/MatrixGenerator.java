package waitNotify;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.StringJoiner;

public class MatrixGenerator {
    private static final String OUTPUT_FILE = "src/main/java/waitNotify/out/matrices";
    private static final int N = 10;
    private static final int NUMBER_OF_MATRIX_PAIRS = 10000;

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(OUTPUT_FILE));

        createMatrices(fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

    private static float[] createRow(Random random) {
        float[] row = new float[N];
        for (int i = 0; i < N; i++) {
            row[i] = random.nextFloat() * random.nextInt(100);
        }
        return row;
    }

    private static Matrix createMatrix(Random random) {
        Matrix matrix = new Matrix(N, N);

        for (int i = 0; i < N; i++) {
            matrix.elements[i] = createRow(random);
        }
        return matrix;
    }

    private static void saveMatrixToFile(FileWriter fileWriter, Matrix matrix) throws IOException {
        for (int r = 0; r < N; r++) {
            StringJoiner stringJoiner = new StringJoiner(", ");
            for (int c = 0; c < N; c++) {
                stringJoiner.add(String.format("%.2f", matrix.elements[r][c]));
            }
            fileWriter.write(stringJoiner.toString());
            fileWriter.write('\n');
        }
        fileWriter.write('\n');
    }

    private static void createMatrices(FileWriter fileWriter) throws IOException {
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_MATRIX_PAIRS * 2; i++) {
            saveMatrixToFile(fileWriter, createMatrix(random));
        }
    }
}
