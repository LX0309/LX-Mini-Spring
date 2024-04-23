package com.lx.springframework.beans.factory.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.DisposableBean;
import com.lx.springframework.beans.factory.ObjectFactory;
import com.lx.springframework.beans.factory.config.SingletonBeanRegistry;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的单例 Bean 注册表实现类
 * 实现了 SingletonBeanRegistry 接口，提供了对单例对象的注册和获取功能
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object(); // 用于占位的 null 对象

    // 一级缓存，存储完全初始化好的单例对象
    /** 单例对象缓存：bean 名称 --> bean 实例 */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 二级缓存，存储提前暴露的单例对象（可能未完全初始化）
    /** 提前暴露的单例对象缓存：bean 名称 --> bean 实例 */
    protected final Map<String, Object> earlySingletonObjects = new HashMap<>();

    // 三级缓存，存储单例工厂，用于创建代理对象
    /** 单例工厂缓存：bean 名称 --> 对象工厂 */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>(); // 存储可处置 Bean

    /**
     * 根据 bean 名称获取单例对象
     * 1. 从一级缓存中获取
     * 2. 如果没有，则从二级缓存中获取（可能为代理对象）
     * 3. 如果还没有，则从三级缓存中获取工厂，使用工厂创建对象，并更新到二级缓存
     */
    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (null == singletonObject) {
            singletonObject = earlySingletonObjects.get(beanName);
            // 判断二级缓存中是否有对象，这个对象就是代理对象，因为只有代理对象才会放到三级缓存中
            if (null == singletonObject) {
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    // 把三级缓存中的代理对象中的真实对象获取出来，放入二级缓存中
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    /**
     * 注册一个单例对象到一级缓存中，并从其他缓存中移除
     */
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    /**
     * 添加一个单例工厂到三级缓存中，并从二级缓存中移除同名的早期单例对象
     */
    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory){
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    /**
     * 注册一个可处置的 Bean
     */
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    /**
     * 销毁所有管理的单例 Bean
     * 1. 获取所有可处置 Bean 的名称
     * 2. 逆序遍历（因为可能存在依赖关系）
     * 3. 调用每个可处置 Bean 的 destroy 方法
     */
    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

}