package atomic;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Metrics metrics = new Metrics();
        MetricsPrinter printer = new MetricsPrinter(metrics);

        BusinessLogic logicThread1 = new BusinessLogic(metrics);
        BusinessLogic logicThread2 = new BusinessLogic(metrics);

        logicThread1.start();
        logicThread2.start();
        printer.start();
    }

    public static class MetricsPrinter extends Thread {
        private Metrics metrics;

        public MetricsPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {

                }
                double currentAverage = metrics.getAverage();

                System.out.println("Current average is: " + currentAverage);
            }
        }
    }

    public static class BusinessLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while(true) {
                long start = System.currentTimeMillis();

                try {
                    Thread.sleep(random.nextInt(2));
                } catch (InterruptedException e) {

                }

                long end = System.currentTimeMillis();

                metrics.addSample(end - start);
            }
        }
    }

    public static class Metrics {
        private long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample) {
            double currentSum = this.average * this.count;
            this.count++;
            this.average = (currentSum + sample) / this.count;
        }

        public double getAverage() {
            return this.average;
        }
    }
}
