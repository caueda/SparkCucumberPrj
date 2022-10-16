package com.example.gft;

import io.vavr.API;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.h2.command.dml.Call;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Data
@Slf4j
public class ThreadTest {

    private final ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Test
    public void testRunnableWithExecutionService() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new EventLoggingTask(map, countDownLatch));
        countDownLatch.await();
        assertTrue(map.containsKey("key"));
        assertEquals("Hello World", map.get("key").toString());
    }

    @Test
    public void testFactorial() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new FactorialTask(12));
        assertEquals(479001600, future.get());
    }

    @Data
    @Slf4j
    public static class EventLoggingTask implements Runnable {
        private ConcurrentHashMap<String, Object> map;
        private CountDownLatch countDownLatch;

        public EventLoggingTask(ConcurrentHashMap map, CountDownLatch countDownLatch) {
            this.map = map;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            log.info(Thread.currentThread().getName() + " is going to sleep");
            API.unchecked(() -> {
                TimeUnit.SECONDS.sleep(3);
                return null;
            }).get();
            log.info("Time to wakeup");
            map.put("key", "Hello World");
            countDownLatch.countDown();
        }
    }

    public static class FactorialTask implements Callable<Integer> {
        private int number;

        public FactorialTask(Integer number) {
            this.number = number;
        }

        @Override
        public Integer call() throws Exception {
            int fact = 1;
            for(int i=number; i>1; i--) {
                fact *= i;
            }
            return fact;
        }
    }

    @Test
    public void testFormatString() {
        String template = "select %s as nome, %s as endereco, %s as idade from dual";
        String[] params = new String[]{"João", "Rua A", "29"};
        System.out.println(String.format(template, Arrays.stream(params).map(v -> String.format("'%s'", v)).toArray(String[]::new)));
    }
}
