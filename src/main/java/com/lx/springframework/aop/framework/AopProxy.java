package com.lx.springframework.aop.framework;

/**
 * AOP代理对象的基本行为
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface AopProxy {
    // 获取代理对象的方法，返回一个代理对象
    Object getProxy();

}
