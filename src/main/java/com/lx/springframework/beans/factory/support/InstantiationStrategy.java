package com.lx.springframework.beans.factory.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 在实例化接口 instantiate 方法中添加必要的入参信息
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface InstantiationStrategy {

    /**
     * 实例化一个 Bean 的策略接口。
     * 该方法根据提供的 Bean 定义、Bean 名称、构造器以及构造器参数来创建并返回一个 Bean 实例。
     *
     * @param beanDefinition Bean 的定义信息，包含了类信息、属性、构造器参数等
     * @param beanName Bean 的名称，可能用于日志记录或错误消息
     * @param ctor Bean 类型的构造器，用于实例化 Bean
     * @param args 构造器所需的参数数组
     * @return 创建的 Bean 实例
     * @throws BeansException 如果实例化过程中发生任何异常
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
