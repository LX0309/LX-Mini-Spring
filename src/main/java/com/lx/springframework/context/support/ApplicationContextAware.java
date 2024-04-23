package com.lx.springframework.context.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.Aware;
import com.lx.springframework.context.ApplicationContext;


/**
 * ApplicationContextAware 接口是一个标识接口，继承自 Aware 接口。
 * 它用于标识那些希望在初始化时获得应用程序上下文（ApplicationContext）的对象。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface ApplicationContextAware extends Aware {

    /**
     * 设置应用程序上下文的方法。当实现了 ApplicationContextAware 接口的对象被初始化时，容器将调用该方法，
     * 并传入当前的应用程序上下文对象。
     *
     * @param applicationContext 当前的应用程序上下文对象
     * @throws BeansException 如果设置应用程序上下文过程中发生异常
     */
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}

