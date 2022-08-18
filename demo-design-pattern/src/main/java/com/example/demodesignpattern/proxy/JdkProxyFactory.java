package com.example.demodesignpattern.proxy;

import java.lang.reflect.Proxy;

/**
 * @author ：zxq
 * @date ：Created in 2021/4/15 19:05
 */

public class JdkProxyFactory {

    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new DebugInvocationHandler(target)
        );
    }
}
