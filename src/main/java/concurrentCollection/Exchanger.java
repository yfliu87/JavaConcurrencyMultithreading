package concurrentCollection;

public class Exchanger {

    public static void main(String[] args) {

        java.util.concurrent.Exchanger<Integer> exchanger = new java.util.concurrent.Exchanger<>();

        new Thread(new Incrementer(exchanger)).start();
        new Thread(new Decrementer(exchanger)).start();
    }

    private static class Incrementer implements Runnable {

        private int counter;
        private java.util.concurrent.Exchanger<Integer> exchanger;

        public Incrementer(java.util.concurrent.Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while(true) {
                counter += 1;

                System.out.println("increase the counter: " + this.counter);

                try {
                    counter = this.exchanger.exchange(counter);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private static class Decrementer implements Runnable {

        private int counter;
        private java.util.concurrent.Exchanger<Integer> exchanger;

        public Decrementer(java.util.concurrent.Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while(true) {
                counter -= 1;

                System.out.println("decrease the counter: " + this.counter);

                try {
                    counter = this.exchanger.exchange(counter);
                } catch (InterruptedException e) {

                }
            }
        }
    }
}
