package stepDefinitions;

import aop.Guitarist;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringConfigurationTest {
    @Test
    public void executeInitMethod() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-xml.xml");
        ctx.refresh();
        Guitarist guitarist = (Guitarist) ctx.getBean("johnMayer");
        System.out.println(guitarist.toString());
    }
}
