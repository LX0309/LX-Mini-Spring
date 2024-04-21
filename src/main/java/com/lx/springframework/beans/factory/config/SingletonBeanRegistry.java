package com.lx.springframework.beans.factory.config;


/**
 * 获取单例对象接口
 */
public interface SingletonBeanRegistry {
    /**
     * 根据 bean 名称获取单例对象
     * @param beanName 要获取的单例对象的名称
     * @return 返回与给定名称关联的单例对象，如果找不到对应的单例对象，则返回 null
     */
    Object getSingleton(String beanName);
    /**
     * 销毁单例对象
     */
    void destroySingletons();
}

