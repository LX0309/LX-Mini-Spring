package com.lx.springframework.beans.factory.config;

/**
 Bean 的定义
 */
public class BeanDefinition {
    //Bean对应的类对象
    private Class beanClass;
    /**
     * 构造方法，初始化 BeanDefinition 对象
     * @param beanClass Bean 对应的类对象
     */
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }
    /**
     * 获取 Bean 对应的类对象
     * @return Bean 对应的类对象
     */
    public Class getBeanClass() {
        return beanClass;
    }
}
