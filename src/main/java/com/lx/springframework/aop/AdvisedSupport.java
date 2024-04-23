package com.lx.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 存储被通知的配置信息
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class AdvisedSupport {

    // 是否使用 CGLIB 代理，默认为 false
    private boolean proxyTargetClass = false;

    // 被代理的目标对象
    private TargetSource targetSource;
    // 方法拦截器
    private MethodInterceptor methodInterceptor;
    // 方法匹配器，用于检查目标方法是否符合通知条件
    private MethodMatcher methodMatcher;

    // 获取是否使用 CGLIB 代理的标识
    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    // 设置是否使用 CGLIB 代理的标识
    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    // 获取被代理的目标对象
    public TargetSource getTargetSource() {
        return targetSource;
    }

    // 设置被代理的目标对象
    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    // 获取方法拦截器
    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    // 设置方法拦截器
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    // 获取方法匹配器
    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    // 设置方法匹配器
    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
