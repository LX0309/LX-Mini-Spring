package com.lx.springframework.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * 反射调用方法的信息，包括目标对象、被调用的方法以及方法的参数
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    // 目标对象
    protected final Object target;
    // 方法
    protected final Method method;
    // 入参
    protected final Object[] arguments;

    // 构造方法，传入目标对象、方法和入参
    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    // 获取被调用的方法
    @Override
    public Method getMethod() {
        return method;
    }

    // 获取方法的入参
    @Override
    public Object[] getArguments() {
        return arguments;
    }

    // 执行方法调用，并返回结果
    @Override
    public Object proceed() throws Throwable {
        // 调用目标对象的方法，并传入参数
        return method.invoke(target, arguments);
    }

    // 获取代理对象
    @Override
    public Object getThis() {
        return target;
    }

    // 获取静态部分，即被调用的方法
    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }

}

