package aop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleStaticPointCut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "sing".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> clazz == GreatGuitarrist.class;
    }
}
