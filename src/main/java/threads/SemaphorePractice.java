package threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphorePractice {
    static class Consumer extends Thread {
        private Semaphore full;
        private Semaphore empty;

        public Consumer(final Semaphore full, final Semaphore empty) {
            this.full = full;
            this.empty = empty;
        }

        public void run() {
            while(true) {
                try {
                    full.acquire();
                    System.out.println("Consuming something " + new java.util.Date());
                    TimeUnit.SECONDS.sleep(1);
                    empty.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class Producer extends Thread {
        private Semaphore full;
        private Semaphore empty;

        public Producer(final Semaphore full, final Semaphore empty) {
            this.full = full;
            this.empty = empty;
        }

        public void run() {
            while(true) {
                try {
                    empty.acquire();
                    System.out.println("Producing something " + new java.util.Date());
                    TimeUnit.SECONDS.sleep(1);
                    full.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Semaphore full = new Semaphore(0);
        Semaphore empty = new Semaphore(1);
        new Consumer(full, empty).start();
        new Producer(full, empty).start();
    }
}
