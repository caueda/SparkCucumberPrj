package aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

@Slf4j
public class SimpleBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.info("Before " + method.getName() + " tune the guitar.");
    }
}
