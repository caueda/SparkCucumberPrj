package com.example.gft;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static io.reactivex.Observable.defer;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class RxJavaTest {
    @Test
    public void functionInterfaceTest() {
        Function<String, String> f = (value) -> {
            if (value == null) {
                return "nada";
            } else {
                return "value = " + value;
            }
        };
        assertEquals("value = hello", f.apply("hello"));
    }

    @Test
    public void supplierInterfaceTest() {
        Supplier<String> sup = () -> "Hello World";
        assertEquals("Hello World", sup.get());
    }

    @Test
    public void charConversion() {
        System.out.println(Character.toString(((char)'a' + 0)));
    }

    @Test
    public void cartesianProduct() {
        Observable<Integer> oneToEight = Observable.range(1, 8);
        Observable<String> ranks = oneToEight
                .map(Object::toString);
        Observable<String> files = oneToEight
                .map(x -> 'a' + x - 1)
//                .doOnNext(x -> System.out.println("a + x - 1 -> " + x))
                .map(ascii -> (char) ascii.intValue())
//                .doOnNext(System.out::println)
                .map(ch -> Character.toString(ch));
        Observable<String> squares = files
                .flatMap(file -> ranks.map(rank -> file + rank));
        squares.subscribe(System.out::println);
    }

    @Test
    public void charCalc() {
        System.out.println('a' + 0);
    }

    @Test
    void testMapGroupBy() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Hello", 1);
        map.put("World", 1);
        Map<Integer, List<String>> collect = map.keySet().stream().collect(Collectors.groupingBy(k -> map.get(k), Collectors.toList()));
        assertEquals(Arrays.asList("Hello", "World"), collect.get(1));
    }

    @Test
    void testSingleMerge() {
        Single<String> s1 = Single.just("Hello").doOnSuccess(e -> String.format("s1 onSuccess %s", e));
        Single<String> s2 = Single.just("World");
        s1.mergeWith(s2).subscribe(System.out::println);
    }

    static void log(Object i) {
        System.out.println(Thread.currentThread().getName() + ": " + i);
    }

    @Test
    void createObservableByDefaultRunsOnMainThread() {
        Observable<Integer> observable = Observable.create(observableEmitter -> {
            log("Starting");
            observableEmitter.onNext(1);
            observableEmitter.onNext(2);
            observableEmitter.onComplete();
            log("Completed");
        });
        observable.subscribe(i -> log(i));
    }

    @Test
    void testBuffer() {
        Observable.range(1, 9)
                .buffer(2, 2)
                .concatMapIterable(x -> x)
                .map(Object::toString)
                .subscribe(System.out::println);
    }

    @Test
    public void testConcatTake() {
        Observable<String> names1 = Observable.create(observableEmitter -> {
            log("Brad");
            observableEmitter.onNext("Brad");
            log("Angelina");
            observableEmitter.onNext("Angelina");
            observableEmitter.onComplete();
            log("Completed");
        });
        Observable<String> names2 = Observable.create(observableEmitter -> {
            log("Jhon");
            observableEmitter.onNext("Jhon");
            log("Snow");
            observableEmitter.onNext("Snow");
            observableEmitter.onComplete();
            log("Completed");
        });
        names1.concatWith(defer(()-> names2))
                .take(2)
                .subscribe(System.out::println);
        System.out.println("--------------------");
        names1.concatWith(names2)
                .take(3)
                .subscribe(System.out::println);
    }

    @Test
    public void testObjectsEquals() {
        assertTrue(Objects.equals(null, null));
        assertFalse(Objects.equals(null, "NAS"));
        assertFalse(Objects.equals("NAS", "TRANSIT"));
    }
    @Test
    public void accessJAVAHOME() {
        log.info("JAVA_HOME {}", System.getenv("JAVA_HOME"));
    }

    @Test
    public void testSubscribeOn() {
        List<String> fruits = List.of("Apple", "Avocado", "Banana", "Grape", "Strawberry", "Papaya", "Cranberry", "Cherry");
        Observable<String> o = Observable.fromIterable(fruits)
                .map(f -> {
                    log.info("Inside map: {}", Thread.currentThread().getName());
                    return f.toUpperCase();
                }).subscribeOn(Schedulers.computation());
        o.toList().subscribe(System.out::println);
    }

    @Test
    public void testMultipleThreadsSubscribeOn() {
        List<String> fruits = List.of("Apple", "Avocado", "Banana", "Grape", "Strawberry", "Papaya", "Cranberry", "Cherry", "Watermelon", "Melon", "Lime", "Orange");
        Observable<String> o = Observable.fromIterable(fruits)
                .flatMap(s -> Observable.just(s).subscribeOn(Schedulers.computation())
                .map(f -> {
                    log.info("Inside map: {}", Thread.currentThread().getName());
                    return f.toUpperCase();
                }));
        o.toList().subscribe(System.out::println);
    }

    @Test
    public void zipWith() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Observable<String> names = Observable.just("Mary", "Patricia", "Linda",
                "Barbara",
                "Elizabeth", "Jennifer", "Maria", "Susan",
                "Margaret", "Dorothy");
        Observable<Long> absoluteDelayMillis = Observable.just(0.1, 0.6, 0.9,
                        1.1,
                        3.3, 3.4, 3.5, 3.6,
                        4.4, 4.8)
                .map(d -> (long) (d * 1000));
        Observable<String> delayedNames = names.zipWith(absoluteDelayMillis, (n,d) ->
                Observable.just(n)
                        .delay(d, TimeUnit.MILLISECONDS)
                ).flatMap(o -> o)
                .doOnComplete(() -> countDownLatch.countDown());

        delayedNames
                .sample(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        countDownLatch.await();
    }
}
