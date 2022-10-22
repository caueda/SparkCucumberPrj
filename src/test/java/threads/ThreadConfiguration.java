package threads;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ComponentScan(basePackages = "threads")
public class ThreadConfiguration {
    @Bean
    public Map<String, String> mapMessage() {
        return Map.of("message", "Hello World from Map");
    }
}
