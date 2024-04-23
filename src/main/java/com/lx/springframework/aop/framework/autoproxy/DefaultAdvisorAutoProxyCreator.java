package com.lx.springframework.aop.framework.autoproxy;


import com.lx.springframework.aop.*;
import com.lx.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.lx.springframework.aop.framework.ProxyFactory;
import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.PropertyValues;
import com.lx.springframework.beans.factory.BeanFactory;
import com.lx.springframework.beans.factory.BeanFactoryAware;
import com.lx.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.lx.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 动创建代理对象，并将其应用于符合条件的 Bean
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    // 用于访问 Spring Bean 工厂
    private DefaultListableBeanFactory beanFactory;

    // 早期代理对象的引用集合
    private final Set<Object> earlyProxyReferences = Collections.synchronizedSet(new HashSet<Object>());

    // 设置 BeanFactory，从 BeanFactoryAware 接口继承而来
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    // 在 Bean 实例化之前不做任何处理，返回 null
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    // 在 Bean 实例化之后不做任何处理，返回 true
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    // 判断给定的类是否是 Spring 基础设施类
    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    // 在 Bean 初始化之前不做任何处理，返回原始的 Bean 实例
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    // 在 Bean 初始化之后，如果 Bean 不在早期代理对象的引用集合中，则尝试包装 Bean 并返回代理对象，否则返回原始的 Bean 实例
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!earlyProxyReferences.contains(beanName)) {
            return wrapIfNecessary(bean, beanName);
        }

        return bean;
    }

    // 根据条件尝试包装 Bean 并返回代理对象
    protected Object wrapIfNecessary(Object bean, String beanName) {
        // 如果 Bean 是 Spring 基础设施类，则直接返回原始的 Bean 实例
        if (isInfrastructureClass(bean.getClass())) return bean;

        // 获取所有匹配的切面
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        // 遍历所有切面
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            // 如果切面匹配当前 Bean，则创建代理对象并返回
            if (classFilter.matches(bean.getClass())) continue;

                AdvisedSupport advisedSupport = new AdvisedSupport();

                TargetSource targetSource = new TargetSource(bean);
                advisedSupport.setTargetSource(targetSource);
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                advisedSupport.setProxyTargetClass(true);

                // 返回代理对象
                return new ProxyFactory(advisedSupport).getProxy();

        }

        // 如果没有匹配的切面，则返回原始的 Bean 实例
        return bean;
    }

    // 在 Bean 实例化过程中尽早获取代理对象的引用，并将其添加到早期代理对象的引用集合中
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) {
        earlyProxyReferences.add(beanName);
        return wrapIfNecessary(bean, beanName);
    }

    // 在 Bean 的属性注入完成后被调用，但在 Bean 的初始化之前返回属性值，这里不做任何处理，直接返回传入的属性值
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

}


