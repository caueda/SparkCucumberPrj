package threads;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ThreadConfigTest.class)
@ComponentScan(basePackages = {"threads"})
public class ThreadConfigTest {
    @Autowired
    private List<String> fruits;
    @Test
    public void printFruits() {
        System.out.println(fruits);
    }
}
