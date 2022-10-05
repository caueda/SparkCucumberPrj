package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AnnotatedAdvice {

    @Pointcut("execution(* com.example.gft.aop..sing*(java.lang.String)) && args(song)")
    public void singWithArgument(String song) {}

    @Pointcut("bean(slash)")
    public void isSlash(){}

    @Before("singWithArgument(song) && isSlash()")
    public void simpleBeforeAdvice(JoinPoint joinPoint, String song){
        System.out.println("Executing " + joinPoint.getSignature().getDeclaringTypeName()
                + joinPoint.getSignature().getName() + " argument: " + song);
    }
}
