package com.lx.springframework.context;

import com.lx.springframework.beans.BeansException;

/**
 * ConfigurableApplicationContext 接口扩展了 ApplicationContext 接口，提供了配置和管理上下文的方法。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器，重新加载配置文件并刷新容器中的 Bean 定义和实例。
     *
     * @throws BeansException 如果刷新过程中发生异常
     */
    void refresh() throws BeansException;

    /**
     * 注册一个 JVM 关闭钩子，当 JVM 关闭时，将关闭 Spring 应用程序上下文。
     */
    void registerShutdownHook();

    /**
     * 关闭 Spring 应用程序上下文，释放资源并销毁 Bean 实例。
     */
    void close();
}
