package com.lx.springframework.beans.factory;

import com.lx.springframework.beans.BeansException;

/**
 * Bean工厂
 */
public interface BeanFactory {
    /**
     获取 Bean 方法
     * @param name bean名称
     * @return bean实例
     * @throws BeansException 自定义异常
     */
    Object getBean(String name) throws BeansException;

    /**
     重载了一个含有入参信息 args 的 getBean 方法，这样就可以方便的传递入参给构造函数实例化了
     * @param name bean名称
     * @param args 入参信息
     * @return bean实例
     * @throws BeansException 自定义异常
     */
    Object getBean(String name, Object... args) throws BeansException;

}
