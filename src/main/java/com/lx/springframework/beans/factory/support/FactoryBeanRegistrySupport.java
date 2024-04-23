package com.lx.springframework.beans.factory.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 提供对工厂Bean的管理
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /**
     * 缓存由 FactoryBean 创建的单例对象：FactoryBean 名称 --> 对象
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    /**
     * 从缓存中获取 FactoryBean 创建的对象。
     * 如果对象存在则返回，否则返回 null。
     */
    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    /**
     * 从 FactoryBean 中获取对象。
     * 如果 FactoryBean 声明为 singleton（单例），则从缓存中获取对象，
     * 否则直接调用 FactoryBean 的 getObject 方法获取对象。
     */
    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factory, beanName);
                // 将获取的对象放入缓存，如果是 null 对象则使用 NULL_OBJECT 替代
                this.factoryBeanObjectCache.put(beanName, (object != null ? object : NULL_OBJECT));
            }
            return (object != NULL_OBJECT ? object : null);
        } else {
            // 对于非单例的 FactoryBean，每次都调用 getObject 方法获取新的实例
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    /**
     * 实际调用 FactoryBean 的 getObject 方法来获取对象。
     * 捕获并处理任何可能发生的异常，将 FactoryBean 创建过程中的异常转换为 BeansException。
     */
    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName){
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object [" + beanName + "] creation", e);
        }
    }

}
