package com.lx.springframework.beans.factory.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.core.io.Resource;
import com.lx.springframework.core.io.ResourceLoader;

/**
 * BeanDefinitionReader 接口定义了加载 Bean 定义的方法。
 * 具体的实现类可以通过不同的方式加载 Bean 定义，例如从资源文件、注解、XML 文件等加载。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface BeanDefinitionReader {

    /**
     * 获取 Bean 定义注册表。
     *
     * @return Bean 定义注册表
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器。
     *
     * @return 资源加载器
     */
    ResourceLoader getResourceLoader();

    /**
     * 从给定的资源加载 Bean 定义。
     *
     * @param resource 资源对象
     * @throws BeansException 如果无法加载 Bean 定义
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 从给定的多个资源加载 Bean 定义。
     *
     * @param resources 资源对象数组
     * @throws BeansException 如果无法加载 Bean 定义
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 根据给定的位置加载 Bean 定义。
     *
     * @param location 资源位置
     * @throws BeansException 如果无法加载 Bean 定义
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 根据给定的多个位置加载 Bean 定义。
     *
     * @param locations 资源位置数组
     * @throws BeansException 如果无法加载 Bean 定义
     */
    void loadBeanDefinitions(String... locations) throws BeansException;

}