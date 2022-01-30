package com.example.gft;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadExample {
    static class ProcessQueue {
        private static final int MAX_ITEMS = 10;
        private Queue queue = new LinkedList();
        private Random random = new Random();

        public synchronized void poll() {
                try {
                    if(queue.isEmpty()) {
                        notify();
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " processing item: " + queue.poll());
        }

        public synchronized void add() {
            try {
                if (queue.size() == MAX_ITEMS) {
                    wait();
                }
                Integer offer = random.nextInt(MAX_ITEMS);
                queue.offer(offer);
                Thread.sleep(1000);
                notifyAll();
                System.out.println(String.format("Item %d number inserted", offer));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Producer extends Thread {
        private ProcessQueue queue;

        public Producer(ProcessQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true) {
                queue.add();
            }
        }
    }

    static class Consumer extends Thread {
        private ProcessQueue queue;

        public Consumer(ProcessQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true) {
                queue.poll();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        ProcessQueue queue = new ProcessQueue();

        Consumer consumer = new Consumer(queue);
        consumer.start();

        Producer producer = new Producer(queue);
        producer.start();



        Consumer consumer1 = new Consumer(queue);
        consumer1.start();
        consumer1.join();


        TimeUnit.SECONDS.sleep(10);

        long end = System.currentTimeMillis();
    }
}
