package threads;

import io.vavr.API;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {

    ExecutorService executor;

    @BeforeEach
    public void init() {
        executor = Executors.newFixedThreadPool(10);
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        executor.submit(() -> {
            API.unchecked(() -> {
                Thread.sleep(5000);
                completableFuture.complete("Hello World");
                return null;
            }).get();
        });

        System.out.println(completableFuture.get());
    }

    @Test
    public void completableFutureAsync() {
        var completable = CompletableFuture.supplyAsync(() -> {
            return API.unchecked(() -> {
                Thread.sleep(2000);
                return "Hello World";
            }).get();
        }, executor).thenApply(String::toUpperCase).thenAccept(System.out::println);

        completable.join();
    }

    @Test
    public void testList() {

        System.out.println(String.format("teste %s", null));
        String values = "LONDON,NEW_YORK";
        var list = List.of(values);
    }
}
