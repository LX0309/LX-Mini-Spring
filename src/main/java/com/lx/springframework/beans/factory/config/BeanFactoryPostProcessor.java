package com.lx.springframework.beans.factory.config;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * BeanFactoryPostProcessor 接口定义了在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，
 * 提供修改 BeanDefinition 属性的机制。通常用于在 Spring 容器加载 BeanDefinition 之后对其进行修改，
 * 比如修改属性值、更改作用域、添加初始化方法等。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制。
     *
     * @param beanFactory Spring BeanFactory 对象，用于获取和修改 BeanDefinition
     * @throws BeansException 如果在 BeanFactoryPostProcessor 的处理过程中发生异常，将抛出 BeansException 异常
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}

