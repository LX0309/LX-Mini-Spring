package com.lx.springframework.beans.factory.config;

import com.lx.springframework.beans.PropertyValues;


/**
 * Bean 定义类，用于描述 Bean 的信息。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class BeanDefinition {

    /** 单例作用域 */
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    /** 原型作用域 */
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    /** Bean 的类 */
    private Class beanClass;

    /** 属性值 */
    private PropertyValues propertyValues;

    /** 初始化方法的名称 */
    private String initMethodName;

    /** 销毁方法的名称 */
    private String destroyMethodName;

    /** 作用域，默认为单例 */
    private String scope = SCOPE_SINGLETON;

    /** 是否是单例 */
    private boolean singleton = true;

    /** 是否是原型 */
    private boolean prototype = false;

    /**
     * 构造方法，创建一个 BeanDefinition 实例。
     *
     * @param beanClass      Bean 的类
     */
    public BeanDefinition(Class beanClass) {
        this(beanClass, null);
    }

    /**
     * 构造方法，创建一个 BeanDefinition 实例。
     *
     * @param beanClass      Bean 的类
     * @param propertyValues 属性值
     */
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = (propertyValues != null) ? propertyValues : new PropertyValues();
    }

    /**
     * 设置作用域。
     *
     * @param scope 作用域
     */
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    /**
     * 判断是否是单例。
     *
     * @return 如果是单例，则返回 true，否则返回 false
     */
    public boolean isSingleton() {
        return singleton;
    }

    /**
     * 判断是否是原型。
     *
     * @return 如果是原型，则返回 true，否则返回 false
     */
    public boolean isPrototype() {
        return prototype;
    }

    /**
     * 获取 Bean 的类。
     *
     * @return Bean 的类
     */
    public Class getBeanClass() {
        return beanClass;
    }

    /**
     * 设置 Bean 的类。
     *
     * @param beanClass Bean 的类
     */
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    /**
     * 获取属性值。
     *
     * @return 属性值
     */
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    /**
     * 设置属性值。
     *
     * @param propertyValues 属性值
     */
    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    /**
     * 获取初始化方法的名称。
     *
     * @return 初始化方法的名称
     */
    public String getInitMethodName() {
        return initMethodName;
    }

    /**
     * 设置初始化方法的名称。
     *
     * @param initMethodName 初始化方法的名称
     */
    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    /**
     * 获取销毁方法的名称。
     *
     * @return 销毁方法的名称
     */
    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    /**
     * 设置销毁方法的名称。
     *
     * @param destroyMethodName 销毁方法的名称
     */
    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }
}

