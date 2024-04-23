package com.lx.springframework.beans.factory;


import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.lx.springframework.beans.factory.config.BeanDefinition;
import com.lx.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * 提供对BeanFactory的控制和和配置的接口
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 获取给定 Bean 名称的 BeanDefinition。
     * 此方法允许访问者检索 Bean 的定义信息，包括 Bean 的类、作用域、生命周期回调等。
     *
     * @param beanName Bean 的注册名称
     * @return BeanDefinition 对象，表示给定名称的 Bean 定义
     * @throws BeansException 如果 beanName 对应的 Bean 定义不存在或在获取过程中发生错误
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 预实例化所有剩余的非惰性单例 Bean。
     * 此方法用于在容器启动时提前初始化所有剩余的非惰性单例 Bean，
     * 以确保它们在第一次请求之前已经创建好。
     * 这通常用于测试或当显式初始化 Bean 很重要时。
     *
     * @throws BeansException 如果在预实例化过程中发生错误
     */
    void preInstantiateSingletons() throws BeansException;

}
