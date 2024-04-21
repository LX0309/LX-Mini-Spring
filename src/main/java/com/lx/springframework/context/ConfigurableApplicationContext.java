package com.lx.springframework.context;

import com.lx.springframework.beans.BeansException;

/**
 * 刷新容器
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
