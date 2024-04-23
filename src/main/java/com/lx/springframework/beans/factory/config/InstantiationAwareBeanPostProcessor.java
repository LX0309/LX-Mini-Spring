package com.lx.springframework.beans.factory.config;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.PropertyValues;

/**
 * 扩展了 BeanPostProcessor 接口的 InstantiationAwareBeanPostProcessor 接口。
 * InstantiationAwareBeanPostProcessor 在 Spring 容器实例化 bean 过程中提供了更细粒度的处理点。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在目标 bean 实例化之前执行的方法。
     * @param beanClass 目标 bean 的类对象
     * @param beanName 目标 bean 的名称
     * @return 可能是目标 bean 的代理对象，用于替代目标 bean
     * @throws BeansException 如果在处理过程中发生异常
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * 在 bean 实例化后、但在 Spring 属性填充之前执行的方法。
     * @param bean 实例化后的 bean 对象
     * @param beanName bean 的名称
     * @return 是否继续处理该 bean
     * @throws BeansException 如果在处理过程中发生异常
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    /**
     * 在将属性应用于 bean 之前执行的方法。
     * @param pvs 要应用的属性值
     * @param bean 实例化后的 bean 对象
     * @param beanName bean 的名称
     * @return 用于应用于 bean 的属性值
     * @throws BeansException 如果在处理过程中发生异常
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

    /**
     * 获取早期的 bean 引用的默认方法。
     * 默认情况下返回原始的 bean 对象。
     * @param bean 实例化后的 bean 对象
     * @param beanName bean 的名称
     * @return 早期的 bean 引用
     */
    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}