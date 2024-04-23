package com.lx.springframework.context.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.lx.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * AbstractRefreshableApplicationContext 是一个抽象类，继承自 AbstractApplicationContext。
 * 它表示可以刷新的应用上下文，其核心是通过刷新 BeanFactory 来更新应用上下文。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    // 存储 DefaultListableBeanFactory 实例的成员变量
    private DefaultListableBeanFactory beanFactory;

    /**
     * 刷新 BeanFactory 的实现方法。在这个方法中，创建了一个新的 DefaultListableBeanFactory，并加载了 Bean 定义。
     * 加载 Bean 定义的具体实现由子类提供。
     *
     * @throws BeansException 如果刷新 BeanFactory 过程中发生异常
     */
    @Override
    protected void refreshBeanFactory() throws BeansException {
        // 创建一个新的 DefaultListableBeanFactory 实例
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        // 加载 Bean 定义
        loadBeanDefinitions(beanFactory);
        // 将新创建的 BeanFactory 赋值给成员变量
        this.beanFactory = beanFactory;
    }

    /**
     * 创建一个新的 DefaultListableBeanFactory 实例。
     *
     * @return 新的 DefaultListableBeanFactory 实例
     */
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 加载 Bean 定义的抽象方法，由子类实现。
     *
     * @param beanFactory BeanFactory 实例
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    /**
     * 获取 BeanFactory 的方法。
     *
     * @return BeanFactory 实例
     */
    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
