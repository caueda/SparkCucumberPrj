package threads;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Basic {
    static int consumers = 1;
    static class SharedObject {
        Random random = new Random();
        Queue queue = new LinkedList();
        private static final Integer MAX_ALLOWED = 10;

        public synchronized void pop() {
            if(queue.size() == 0) {
                notify();
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting...");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("Popping %d from the queue by %s", queue.poll(), Thread.currentThread().getName()));
        }

        public synchronized void add() {
            try {
                if(queue.size() == MAX_ALLOWED) {
                    wait();
                }
                Integer num = random.nextInt(11);
                queue.add(num);
                notifyAll();
                System.out.println(String.format("Adding %d -  Queue.size %d", num, queue.size()));
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ConsumerThread extends Thread {
        private SharedObject sharedObject;

        public ConsumerThread(SharedObject sharedObject) {
            this.sharedObject = sharedObject;
            this.setName("Consumer-Thread-" + consumers++);
            setDaemon(true);
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " about to start");
            while(true) {
                try {
                    sharedObject.pop();
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ProducerThread extends Thread {
        private SharedObject sharedObject;

        public ProducerThread(SharedObject sharedObject) {
            this.sharedObject = sharedObject;
            this.setName("Producer-Thread");
            setDaemon(true);
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " about to start");
            while(true) {
                try  {
                    sharedObject.add();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SharedObject sharedObject = new SharedObject();
        new ConsumerThread(sharedObject).start();
        new ConsumerThread(sharedObject).start();
        new ProducerThread(sharedObject).start();
        TimeUnit.SECONDS.sleep(60);
    }
}
