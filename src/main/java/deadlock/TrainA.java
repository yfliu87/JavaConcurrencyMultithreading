package deadlock;

import java.util.Random;

public class TrainA implements Runnable {
    private Intersection intersection;
    private Random random = new Random();

    public TrainA(Intersection intersection) {
        this.intersection = intersection;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(5));
            } catch (InterruptedException e) {

            }
            this.intersection.takeRoadA();
        }
    }
}
