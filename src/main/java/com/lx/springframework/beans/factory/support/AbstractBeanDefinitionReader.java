package com.lx.springframework.beans.factory.support;

import com.lx.springframework.core.io.DefaultResourceLoader;
import com.lx.springframework.core.io.ResourceLoader;


/**
 * 抽象的 Bean 定义读取器，实现了 BeanDefinitionReader 接口。
 * 用于读取和解析 Bean 定义，并将其注册到 BeanDefinitionRegistry 中。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    /** BeanDefinitionRegistry 实例，用于注册 Bean 定义 */
    private final BeanDefinitionRegistry registry;

    /** 资源加载器，用于加载 Bean 定义的资源 */
    private ResourceLoader resourceLoader;

    /**
     * 构造方法，使用指定的 BeanDefinitionRegistry 和默认的资源加载器。
     *
     * @param registry BeanDefinitionRegistry 实例，用于注册 Bean 定义
     */
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    /**
     * 构造方法，使用指定的 BeanDefinitionRegistry 和资源加载器。
     *
     * @param registry      BeanDefinitionRegistry 实例，用于注册 Bean 定义
     * @param resourceLoader 资源加载器，用于加载 Bean 定义的资源
     */
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    /**
     * 获取 BeanDefinitionRegistry 实例。
     *
     * @return BeanDefinitionRegistry 实例
     */
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    /**
     * 获取资源加载器。
     *
     * @return 资源加载器
     */
    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}

