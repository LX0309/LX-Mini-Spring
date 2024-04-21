package com.lx.springframework.beans.factory.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.DisposableBean;
import com.lx.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 默认的单例 Bean 注册表实现类
 * 实现了 SingletonBeanRegistry 接口，提供了对单例对象的注册和获取功能
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    // 用于存储单例对象的容器，键为 bean 名称，值为对应的单例对象
    private Map<String, Object> singletonObjects = new HashMap<>();


    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();
    /**
     * 根据 bean 名称获取单例对象
     *
     * @param beanName 要获取的单例对象的名称
     * @return 返回与给定名称关联的单例对象，如果找不到对应的单例对象，则返回 null
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 向单例对象容器中添加单例对象
     *
     * @param beanName        要添加的单例对象的名称
     * @param singletonObject 要添加的单例对象
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
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
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

}

