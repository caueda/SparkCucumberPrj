package aop.configaop;

import aop.AnnotatedAdvice;
import aop.GreatGuitarrist;
import aop.Guitarist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@Slf4j
@EnableAspectJAutoProxy
public class AOPConfig {

    @Bean("message")
    public String message() {
        log.info("message initialized.");
        return "Hello World";
    }

    @Bean("johnMayer")
    public Guitarist johnMayer() {
        return new Guitarist();
    }

    @Bean("slash")
    public GreatGuitarrist slash() {
        return new GreatGuitarrist();
    }

    @Bean
    public AnnotatedAdvice annotatedAdvice() {
        return new AnnotatedAdvice();
    }
}
