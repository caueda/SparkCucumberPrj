package com.example.gft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.example.gft.config.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.vavr.API;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;

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
}
