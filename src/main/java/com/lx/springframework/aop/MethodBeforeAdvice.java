package com.lx.springframework.aop;

import java.lang.reflect.Method;

/**
 * 方法前置通知的类型
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * 在给定方法调用之前进行回调。
     *
     * @param method 被调用的方法
     * @param args   方法的参数
     * @param target 方法调用的目标对象。可能为 <code>null</code>。
     * @throws Throwable 如果此对象希望中止调用。
     *                   如果方法签名允许，任何抛出的异常都将返回给调用者。
     *                   否则，异常将被包装为运行时异常。
     */
    void before(Method method, Object[] args, Object target) throws Throwable;

}
