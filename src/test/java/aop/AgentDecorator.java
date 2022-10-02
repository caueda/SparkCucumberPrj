package aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AgentDecorator implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StringBuilder newString = new StringBuilder("James ");
        newString.append(invocation.proceed());
        newString.append("!");
        return newString.toString();
    }
}
