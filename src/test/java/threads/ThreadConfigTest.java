package threads;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ThreadConfiguration.class})
public class ThreadConfigTest {

    @Autowired
    private MessageBean messageBean;

    @Autowired
    private Map<String, String> mapMessage;

    @Test
    public void sayMessage() {
        System.out.println(mapMessage.get("message"));
        System.out.println(messageBean.message());
    }
}
