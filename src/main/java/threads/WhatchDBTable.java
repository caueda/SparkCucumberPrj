package threads;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jdbc.Work;

import java.nio.file.*;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class WhatchDBTable {


    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(new Worker(countDownLatch)).start();

        countDownLatch.await();
    }

    static class Worker implements Runnable {
        private CountDownLatch countDownLatch;
        public Worker(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            Try.of(()-> {
                System.out.println("Start sleep");
                Thread.sleep(3 * 1000);
                System.out.println("End sleep");
                countDownLatch.countDown();
                return null;
            });
        }
    }
}
