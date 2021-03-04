package multithread.imageProcessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String SOURCE_FILE = "src/main/java/multithread/imageProcessing/resources/flowers.jpg";
    public static final String DESTINATION_FILE = "src/main/java/multithread/imageProcessing/out/flowers.jpg";
    public static ImageProcessor processor = new ImageProcessor();

    public static void main(String[] args) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
        BufferedImage resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        singleThreadProcessing(originalImage, resultImage);

        multiThreadProcessing(originalImage, resultImage, 4);

    }

    private static void singleThreadProcessing(BufferedImage originalImage, BufferedImage resultImage) throws IOException {

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        long startTime = System.currentTimeMillis();

        processor.recolorImage(originalImage, resultImage, 0, 0, width, height);

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        File outputFile = new File(DESTINATION_FILE);
        ImageIO.write(resultImage, "jpg", outputFile);

        System.out.println("single thread processing duration: " + duration);
    }

    private static void multiThreadProcessing(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) throws IOException {

        List<Thread> threads = new ArrayList<>();
        int width = originalImage.getWidth();
        int height = originalImage.getHeight() / numberOfThreads;

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadMultiplier = i;

            Thread thread = new Thread(() -> {
               int xOrigin = 0;
               int yOrigin = height * threadMultiplier;

               processor.recolorImage(originalImage, resultImage, xOrigin, yOrigin, width, height);
            });

            threads.add(thread);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch(InterruptedException e) {

            }
        }

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        File outputFile = new File(DESTINATION_FILE);
        ImageIO.write(resultImage, "jpg", outputFile);

        System.out.println("multi-thread processing duration: " + duration);
    }

}
