package com.lx.springframework.beans.factory.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Bean实例化策略
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    /**
     * 实现 InstantiationStrategy 接口的简单实例化策略。
     * 使用此策略，Bean 可以通过指定的构造器参数或无参构造器进行实例化。
     */

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        // 获取 Bean 定义中的类对象
        Class<?> clazz = beanDefinition.getBeanClass();
        try {
            // 如果提供了构造器和参数，则通过反射调用该构造器创建实例
            if (null != ctor) {
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                // 如果没有提供构造器（即无参构造器），则直接创建实例
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            // 捕获实例化过程中可能抛出的任何异常，并将其封装为 BeansException 抛出
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }

}

