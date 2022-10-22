package threads;

import org.springframework.stereotype.Component;

@Component
public class MessageBean {
    public String message() {
        return "Hello World";
    }
}
