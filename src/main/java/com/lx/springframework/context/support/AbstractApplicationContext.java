package com.lx.springframework.context.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.lx.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.lx.springframework.beans.factory.config.BeanPostProcessor;
import com.lx.springframework.context.ConfigurableApplicationContext;
import com.lx.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 提供应用上下文的生命周期管理，包括初始化、刷新、获取 Bean、关闭等操作
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    /**
     * 刷新容器，准备 BeanFactory，并触发 BeanFactoryPostProcessor、BeanPostProcessor 等的执行。
     * 1. 创建 BeanFactory 并加载 BeanDefinition。
     * 2. 获取 BeanFactory 引用。
     * 3. 添加 ApplicationContextAwareProcessor。
     * 4. 执行 BeanFactoryPostProcessor。
     * 5. 注册 BeanPostProcessor。
     * 6. 预实例化单例 Bean。
     */
    @Override
    public void refresh() throws BeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();

        // 2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 6. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 抽象方法，用于刷新 BeanFactory，由子类实现。
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 抽象方法，用于获取 ConfigurableListableBeanFactory，由子类实现。
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 调用所有 BeanFactoryPostProcessor。
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册所有的 BeanPostProcessor。
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }


    // 以下是 ConfigurableApplicationContext 接口的方法实现，它们委托给 BeanFactory 执行。
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }
    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }

}
