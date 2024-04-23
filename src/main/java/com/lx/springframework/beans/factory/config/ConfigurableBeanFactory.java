package com.lx.springframework.beans.factory.config;

import com.lx.springframework.beans.factory.HierarchicalBeanFactory;
import com.lx.springframework.utils.StringValueResolver;

/**
 * ConfigurableBeanFactory 接口扩展了 HierarchicalBeanFactory 和 SingletonBeanRegistry 接口，提供了对 Bean 工厂进行配置的能力。
 * 允许添加 BeanPostProcessor 以及销毁单例对象等功能。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /** 单例作用域 */
    String SCOPE_SINGLETON = "singleton";

    /** 原型作用域 */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 向 Bean 工厂添加 BeanPostProcessor。
     *
     * @param beanPostProcessor 要添加的 BeanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁所有单例对象。
     */
    void destroySingletons();

    /**
     * 添加一个用于解析嵌入值（例如注解属性）的字符串解析器。
     *
     * @param valueResolver 要应用于嵌入值的字符串解析器
     * @since 3.0
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * 解析给定的嵌入值，例如注解属性。
     *
     * @param value 要解析的值
     * @return 解析后的值（可能是原始值）
     * @since 3.0
     */
    String resolveEmbeddedValue(String value);
}
