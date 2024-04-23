package com.lx.springframework.beans.factory.support;


import cn.hutool.core.util.StrUtil;
import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.DisposableBean;
import com.lx.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 包装Bean
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;  // 被包装的 Bean 实例
    private final String beanName;  // Bean 的名称
    private String destroyMethodName;  // Bean 的销毁方法名称

    /**
     * 构造函数
     * @param bean 需要被包装的 Bean 实例
     * @param beanName Bean 的名称
     * @param beanDefinition Bean 的定义信息，从中可以获取到 Bean 的销毁方法名称
     */
    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();  // 获取销毁方法名称
    }

    /**
     * 销毁 Bean 实现
     * 1. 如果 Bean 本身实现了 DisposableBean 接口，则直接调用其 destroy 方法
     * 2. 如果指定了注解配置的销毁方法，则通过反射调用该方法
     */
    @Override
    public void destroy() throws Exception {
        // 如果 Bean 实现了 DisposableBean 接口，则调用其 destroy 方法
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 如果指定了销毁方法名称，并且 Bean 没有实现 DisposableBean 接口，或者销毁方法名称不是默认的 "destroy"，则通过反射调用销毁方法
        if (StrUtil.isNotEmpty(destroyMethodName) &&
                !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (destroyMethod == null) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName +
                        "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }

    }

}
