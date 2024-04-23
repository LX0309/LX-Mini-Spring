package com.lx.springframework.beans.factory.config;


import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.BeanFactory;

/**
 * 自动装配能力的 Bean 工厂接口，继承自 BeanFactory。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 在初始化之前应用 Bean 后置处理器。
     *
     * @param existingBean 待初始化的 Bean 实例
     * @param beanName     Bean 的名称
     * @return 初始化之前的 Bean 实例
     * @throws BeansException 如果在处理过程中发生异常
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 在初始化之后应用 Bean 后置处理器。
     *
     * @param existingBean 待初始化的 Bean 实例
     * @param beanName     Bean 的名称
     * @return 初始化之后的 Bean 实例
     * @throws BeansException 如果在处理过程中发生异常
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
