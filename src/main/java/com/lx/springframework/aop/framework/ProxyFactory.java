package com.lx.springframework.aop.framework;


import com.lx.springframework.aop.AdvisedSupport;

/**
 * 根据给定的被通知配置信息创建代理对象
 *
 *
 *
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class ProxyFactory {

    // 持有被通知的配置信息
    private AdvisedSupport advisedSupport;

    // 构造方法，传入被通知的配置信息
    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    // 获取代理对象的方法
    public Object getProxy() {
        // 调用 createAopProxy 方法创建 AopProxy 实例，并调用其 getProxy 方法获取代理对象
        return createAopProxy().getProxy();
    }

    // 创建 AopProxy 实例的方法
    private AopProxy createAopProxy() {
        // 使用 JDK 动态代理创建 AopProxy 实例
        return new JdkDynamicAopProxy(advisedSupport);
    }

}
