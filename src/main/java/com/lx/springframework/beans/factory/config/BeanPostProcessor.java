package com.lx.springframework.beans.factory.config;

import com.lx.springframework.beans.BeansException;

/**
 * BeanPostProcessor 接口定义了在 Bean 对象执行初始化方法之前和之后执行的方法，用于在 Bean 的生命周期中对 Bean 进行额外的处理。
 * 典型的使用场景包括对 Bean 进行属性填充、初始化前后的额外处理等。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法。
     *
     * @param bean     待处理的 Bean 对象
     * @param beanName Bean 的名称
     * @return 经过处理后的 Bean 对象，可以是原始的 Bean 对象或者经过修改后的新对象
     * @throws BeansException 如果在处理过程中发生异常，将抛出 BeansException 异常
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法。
     *
     * @param bean     待处理的 Bean 对象
     * @param beanName Bean 的名称
     * @return 经过处理后的 Bean 对象，可以是原始的 Bean 对象或者经过修改后的新对象
     * @throws BeansException 如果在处理过程中发生异常，将抛出 BeansException 异常
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}


