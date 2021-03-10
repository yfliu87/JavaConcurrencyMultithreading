package concurrentCollection;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMap {

    public static void main(String[] args) {
        java.util.concurrent.ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        new Thread(new Producer(map)).start();
        new Thread(new Consumer(map)).start();
    }

    private static class Producer implements Runnable {

        private java.util.concurrent.ConcurrentMap<String, Integer> map;

        public Producer(java.util.concurrent.ConcurrentMap<String, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            try {
                map.put("Monday", 1);
                Thread.sleep(1000);
                map.put("Tuesday", 2);
                Thread.sleep(1000);
                map.put("Wednesday", 3);
                Thread.sleep(1000);
                map.put("Thursday", 4);
                Thread.sleep(1000);
                map.put("Friday", 5);
            } catch (InterruptedException e) {

            }
        }
    }

    private static class Consumer implements Runnable {

        private java.util.concurrent.ConcurrentMap<String, Integer> map;

        public Consumer(java.util.concurrent.ConcurrentMap<String, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            try {
                System.out.println(map.get("Friday"));
                Thread.sleep(1000);
                System.out.println(map.get("Tuesday"));
                Thread.sleep(1000);
                System.out.println(map.get("Thursday"));
                Thread.sleep(1000);
                System.out.println(map.get("Monday"));
                Thread.sleep(1000);
                System.out.println(map.get("Wednesday"));
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }

    }
}
