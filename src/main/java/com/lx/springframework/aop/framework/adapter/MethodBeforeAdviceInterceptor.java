package com.lx.springframework.aop.framework.adapter;


import com.lx.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * MethodBeforeAdviceInterceptor 类是 MethodInterceptor 接口的实现，用于在方法执行之前执行相应的通知逻辑。
 * 它包含了一个 MethodBeforeAdvice 对象，用于执行前置通知。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    // 前置通知对象
    private MethodBeforeAdvice advice;

    /**
     * 默认构造函数。
     */
    public MethodBeforeAdviceInterceptor() {
    }

    /**
     * 带有 MethodBeforeAdvice 参数的构造函数，用于设置前置通知对象。
     * @param advice 前置通知对象
     */
    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    /**
     * 在方法执行前执行通知逻辑。
     * 调用前置通知对象的 before 方法，然后继续执行原始方法。
     * @param methodInvocation 方法调用对象
     * @return 原始方法的返回值
     * @throws Throwable 如果在通知逻辑中发生异常，则抛出异常
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        this.advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }

    /**
     * 获取前置通知对象。
     * @return 前置通知对象
     */
    public MethodBeforeAdvice getAdvice() {
        return advice;
    }

    /**
     * 设置前置通知对象。
     * @param advice 前置通知对象
     */
    public void setAdvice(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

}