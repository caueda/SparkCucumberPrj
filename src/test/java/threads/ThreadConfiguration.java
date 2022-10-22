package threads;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestConfiguration
public class ThreadConfiguration {
    @Bean
    public List<String> fruits() {
        return Stream.of("Apple", "Orange", "Avocado").collect(Collectors.toList());
    }
}
