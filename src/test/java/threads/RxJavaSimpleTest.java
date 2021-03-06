package threads;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.vavr.Tuple2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class RxJavaSimpleTest {
    @Test
    public void testObservable() {
        Observable.just(Observable.just("Test"))
                .doOnNext(s -> {
                    System.out.println(s.getClass().getName());
                })
                .compose(RxJavaSimpleTest::getLongObservable)
                .doOnNext(l -> log.info("log value {}", l))
                .doOnNext(l -> log.info("Record it into database {}", l))
                .subscribe();
    }

    public static Observable<Long> getLongObservable(Observable<?> obs) {
        return Observable.fromArray(new Long[]{1L, 2L, 3L});
    }

    @Test
    public void mapAndFilter() {
        Observable
                .just(8, 9, 10)
                .doOnNext(i -> System.out.println("A: " + i))
                .filter(i -> i % 3 > 0)
                .doOnNext(i -> System.out.println("B: " + i))
                .map(i -> "#" + i * 10)
                .doOnNext(s -> System.out.println("C: " + s))
                .filter(s -> s.length() < 4)
                .subscribe(s -> System.out.println("D: " + s));
    }

    @Test
    public void zipWith() {
        Observable<Integer> integers = Observable.fromArray(new Integer[]{1,2,3,4,5,6,7,8});
        Observable<Boolean> booleans = Observable.fromArray(new Boolean[]{true, false, true, false, true, false, true, false});
        List<Integer> processedList = new ArrayList<>();
        BigDecimal result = integers.zipWith(booleans, (i, b)-> new Tuple2<Integer, Boolean>(i,b))
                .filter(tuple2 -> tuple2._2)
                .map(tuple2 -> BigDecimal.valueOf(tuple2._1))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .blockingGet();
        assertEquals(BigDecimal.valueOf(16), result);
    }

    @Test
    public void applyFunction() {
        Function<Integer, String> function = i -> String.format("This is a number %d", i);
        assertEquals("This is a number 5", function.apply(5));
    }

    @Test
    public void recomendBook() {
        Observable<String> recommend = Observable.create((stringEmitter -> {
            stringEmitter.onError(new IllegalArgumentException("Sorry, a error has happended"));
        }));
        Observable<String> bestSeller = Observable.just("Lord of the Rings");
        Observable<String> book = recommend.doOnError(System.out::println).onErrorResumeNext(bestSeller);
        String result = book.blockingFirst();
        assertEquals("Lord of the Rings", result);
    }

    @Test
    public void flowableTest() {
        Flowable<String> test = Flowable.just("Hello");
    }
}