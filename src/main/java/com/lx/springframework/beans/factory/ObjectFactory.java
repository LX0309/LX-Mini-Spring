package com.lx.springframework.beans.factory;

import com.lx.springframework.beans.BeansException;

/**
 * 提供了一个通用的方法来获取对象实例
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface ObjectFactory<T> {

    /**
     * 获取类型为 T 的对象实例。
     * 此方法由 Spring 框架调用，用于从 ObjectFactory 中获取一个对象实例。
     * 与直接使用 BeanFactory 获取 Bean 不同，ObjectFactory 允许更细粒度的控制，
     * 比如延迟初始化、或者为每个调用创建独立的对象实例。
     *
     * @return 返回的对象实例，类型为 T
     * @throws BeansException 如果在获取对象实例过程中发生错误，则抛出此异常
     */
    T getObject() throws BeansException;

}