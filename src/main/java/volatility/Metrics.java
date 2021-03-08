package volatility;

public class Metrics {
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
