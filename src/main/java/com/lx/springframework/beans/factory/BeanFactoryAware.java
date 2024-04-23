package com.lx.springframework.beans.factory;

import com.lx.springframework.beans.BeansException;

/**
 * 实现此接口，既能感知到所属的 BeanFactory
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface BeanFactoryAware extends Aware {

    /**
     * 设置 BeanFactory。
     * 此方法由 Spring 容器在 Bean 实例化和初始化之后调用，
     * 允许 Bean 明确地访问 BeanFactory，例如用于查找其他 Bean 或获取配置信息。
     *
     * @param beanFactory 提供对 BeanFactory 的引用，BeanFactory 是 Spring 容器的中心接口，
     *                    负责管理 Bean 的生命周期和依赖关系。
     * @throws BeansException 如果调用者在设置 BeanFactory 时发生任何异常，则抛出此异常。
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}