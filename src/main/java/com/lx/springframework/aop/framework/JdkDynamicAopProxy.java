package com.lx.springframework.aop.framework;

import com.lx.springframework.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于JDK动态代理实现的AOP代理对象生成器
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    // 持有被通知的配置信息
    private final AdvisedSupport advised;

    // 构造方法，传入被通知的配置信息
    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    // 实现 AopProxy 接口的方法，用于获取代理对象
    @Override
    public Object getProxy() {
        // 使用 Proxy.newProxyInstance 方法创建代理对象
        // 传入当前线程的类加载器、目标类以及代理对象自身（InvocationHandler）
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advised.getTargetSource().getTargetClass(), this);
    }

    // 实现 InvocationHandler 接口的方法，处理代理对象的方法调用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 检查方法是否匹配通知条件
        if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
            // 如果方法匹配通知条件，则调用方法拦截器进行增强处理
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
        }
        // 如果方法不匹配通知条件，则直接调用目标对象的方法
        return method.invoke(advised.getTargetSource().getTarget(), args);
    }

}
