package com.lx.springframework.beans.factory.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.core.io.Resource;
import com.lx.springframework.core.io.ResourceLoader;

/**
 * Bean定义读取接口
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

}

