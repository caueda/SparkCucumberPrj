package aop;

import aop.configaop.AOPConfig;
import org.aopalliance.aop.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AOPConfig.class)
@ComponentScan("aop")
public class AOPTest {

    public AOPTest(@Qualifier("johnMayer") Guitarist johnMayer, @Qualifier("slash") GreatGuitarrist slash,
                   @Qualifier("message") String message) {
        this.johnMayer = johnMayer;
        this.slash = slash;
        this.message = message;
    }

    private Guitarist johnMayer;
    private GreatGuitarrist slash;
    private String message;

    @Test
    public void slashSing() {
        System.out.println(message);
        Objects.nonNull(this.slash);
        slash.sing("sweet child of mine");
    }

    @Test
    public void testAgentDecoratorAdvice() {
        Agent target = new Agent();
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new AgentDecorator());
        pf.setTarget(target);
        Agent proxy = (Agent) pf.getProxy();
        assertThat(target.speak(), equalTo("Bond"));
        assertThat(proxy.speak(), equalTo("James Bond!"));
    }

    @Test
    public void testGuitarristSimpleBeforeAdvice(){
        Guitarist johnMayer = new Guitarist();
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SimpleBeforeAdvice());
        pf.setTarget(johnMayer);
        Guitarist proxy = (Guitarist) pf.getProxy();
        proxy.sing();
    }

    @Test
    public void testSubstitution() {
        String expected = "row:config";
        assertThat(expected, equalTo(String.format("%sconfig", "row:")));
    }
    @Test
    public void testSimpleStaticPointCut() {
        Guitarist guitarrist = new Guitarist();
        GreatGuitarrist greatGuitarrist = new GreatGuitarrist();
        Singer proxyOne = null;
        Singer proxyTwo = null;
        Pointcut pc = new SimpleStaticPointCut();
        Advice agentDecorator = new AgentDecorator();
        Advisor advisor = new DefaultPointcutAdvisor(pc, agentDecorator);

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(guitarrist);
        proxyOne = (Singer) pf.getProxy();

        pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(greatGuitarrist);
        proxyTwo = (Singer) pf.getProxy();

        proxyOne.sing();
        proxyTwo.sing();
    }
}
