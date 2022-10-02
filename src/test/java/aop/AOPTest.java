package aop;

import org.aopalliance.aop.Advice;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AOPTest {
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
        Guitarrist johnMayer = new Guitarrist();
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SimpleBeforeAdvice());
        pf.setTarget(johnMayer);
        Guitarrist proxy = (Guitarrist) pf.getProxy();
        proxy.sing();
    }

    @Test
    public void testSimpleStaticPointCut() {
        Guitarrist guitarrist = new Guitarrist();
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
