package com.lx.springframework.beans.factory.config;

import com.lx.springframework.beans.PropertyValues;

/**
 Bean 的定义
 */
public class BeanDefinition {
    //Bean对应的类对象
    private Class beanClass;

    private PropertyValues propertyValues;


    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }
    /**
     * 构造方法，初始化 BeanDefinition 对象
     * @param beanClass Bean 对应的类对象
     */
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }
    /**
     * 获取 Bean 对应的类对象
     * @return Bean 对应的类对象
     */
    public Class getBeanClass() {
        return beanClass;
    }
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
